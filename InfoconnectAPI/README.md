This is a work in progress at this point.
	TODO: 
	- implement remaining search fields for Companies 
	- implement remaining search fields for People 
	- implement SICs API call 
	- implement XML versions of output?

The API makes use of the GSON library (https://code.google.com/p/google-gson/) to convert the java objects into JSON for passing into the API. This makes it easy to add new fields to the search and output. It also means that the need to handle both JSON and XML input isn't as important since a user would be working directly with Java objects.
