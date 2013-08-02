The primary goal for this project is to make it easy to use the Infoconnect API in a Java project without having to fuss with all the 
formatting of inputs or working with the HTTP calls directly. There are a number of fields in the POST APIs that expect data
to be in a predefined range of values, or submitted as an array, or other object. This API should encapsulate all that and 
make it less likely the user will mistakenly send an incorrect value.
	
	TODO: 
	[ ] Proper error handling from GET and POST
	[ ] implement XML versions of output?

The API makes use of the GSON library (https://code.google.com/p/google-gson/) to convert the java 
objects into JSON for getting data into and out of the API. This makes it easy to add new fields to the search and output. 
It also means that the need to handle both JSON and XML input isn't as important since a user would be working directly with Java objects.

**_NOTE:_** One side effect of using GSON is that the field names in the Java object need to match the case of the JSON output exactly.
Changing the field names in the types to camel-case or something like that will cause the GSON deserializer to silently fail and not
convert those fields from the JSON response.
