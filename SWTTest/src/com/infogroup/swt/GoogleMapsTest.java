package com.infogroup.swt;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTError;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.infogroup.api.InfoconnectAPI;
import com.infogroup.api.records.Company;
import com.infogroup.api.records.Person;
import com.infogroup.api.searchtypes.CompanySearch;
import com.infogroup.api.searchtypes.Search;
import com.infogroup.api.types.GeoPoint;

public class GoogleMapsTest {

	static List list;
	static Browser browser;
	static InfoconnectAPI api = new InfoconnectAPI();
	static Text hitCount;

	public static void main(String[] args) throws IOException {
		api.setApiKey(args[0]);

		File f = new File("data/map.html");
		if (!f.exists()) {
			System.out.println("file not exist! " + f.getAbsolutePath());
			return;
		}
		Display display = new Display();
		final Shell shell = new Shell(display);
		shell.setLayout(new FillLayout());
		SashForm sash = new SashForm(shell, SWT.HORIZONTAL);

		try {
			browser = new Browser(sash, SWT.NONE);
			browser.addControlListener(new ControlListener() {

				public void controlResized(ControlEvent e) {
					browser.execute("document.getElementById('map_canvas').style.width= " + (browser.getSize().x - 20) + ";");
					browser.execute("document.getElementById('map_canvas').style.height= " + (browser.getSize().y - 20) + ";");
				}

				public void controlMoved(ControlEvent e) {
				}
			});
		} catch (SWTError e) {
			System.out.println("Could not instantiate Browser: " + e.getMessage());
			display.dispose();
			return;
		}

		final Composite c = new Composite(sash, SWT.BORDER);
		c.setLayout(new GridLayout(1, true));
		Button b = new Button(c, SWT.PUSH);
		Composite inner = new Composite(c, SWT.None);
		inner.setLayout(new GridLayout(2, true));
		Label l = new Label(inner, SWT.None);
		l.setText("Total hits: ");
		hitCount = new Text(inner, SWT.None | SWT.RIGHT);
		hitCount.setText("0");
		hitCount.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		l.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		l = new Label(c, SWT.None);
		l.setText("First 10 hits:");
		list = new List(c, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
		list.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		b.setText("Who is here?");
		b.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				DecimalFormat df = new DecimalFormat("#.00000");
				browser.evaluate("clearMarkers();");
				list.removeAll();
				Double lat = ((Double) browser.evaluate("return map.getCenter().lat();"));
				Double lng = ((Double) browser.evaluate("return map.getCenter().lng();"));
				Double neLat = ((Double) browser.evaluate("return lastBounds.getNorthEast().lat();"));
				Double neLng = ((Double) browser.evaluate("return lastBounds.getNorthEast().lng();"));
				Double swLat = ((Double) browser.evaluate("return lastBounds.getSouthWest().lat();"));
				Double swLng = ((Double) browser.evaluate("return lastBounds.getSouthWest().lng();"));
				System.out.println(neLat + "," + neLng + " - " + swLat + "," + swLng);

				CompanySearch cs = new CompanySearch();
				cs.setResourceType(Search.RESOURCE_TYPE_BASIC);

				try {
					java.util.List<GeoPoint> pts = new ArrayList<GeoPoint>();
					pts.add(new GeoPoint(df.format(neLat), df.format(swLng)));
					pts.add(new GeoPoint(df.format(neLat), df.format(neLng)));
					pts.add(new GeoPoint(df.format(swLat), df.format(swLng)));
					pts.add(new GeoPoint(df.format(swLat), df.format(neLng)));
					cs.addPolygon(pts);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				int count = api.count(cs);
				hitCount.setText(Integer.toString(count));
				cs.setResourceType(Search.RESOURCE_TYPE_BASIC);

				java.util.List<Company> companies = api.companies(cs);
				if (null != companies) {
					for (Company c : companies) {
						String popupText = getMarkupText(c);
						browser.evaluate("addMarker(" + c.Location.Latitude + "," + c.Location.Longitude + ",\"" + popupText + "\")");
						list.add(c.CompanyName);
					}
				}

			}

			private String getMarkupText(Person p) {
				StringBuilder sb = new StringBuilder();
				sb.append("<h3>" + p.FirstName + " " + p.LastName + "</h3>");
				sb.append("<p>Address: " + p.Address + "<br/>");
				sb.append(p.City + ", " + p.StateProvince + " " + p.PostalCode + "<br/>");
				if (null != p.Phone && !p.Phone.isEmpty()) {
					sb.append("Phone: <a href='tel:" + p.Phone + "'>" + p.Phone + "</a><br/>");
				}
				return sb.toString();
			}

			private String getMarkupText(Company c) {
				StringBuilder sb = new StringBuilder();
				sb.append("<h3>" + c.CompanyName + "</h3>");
				sb.append("<p>Address: " + c.Address + "<br/>");
				sb.append(c.City + ", " + c.StateProvince + " " + c.PostalCode + "<br/>");
				if (null != c.Phone && !c.Phone.isEmpty()) {
					sb.append("Phone: <a href='tel:" + c.Phone + "'>" + c.Phone + "</a><br/>");
				}
				return sb.toString();
			}
		});

		browser.setUrl(f.toURI().toString());
		sash.setWeights(new int[] { 4, 1 });
		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}
}
