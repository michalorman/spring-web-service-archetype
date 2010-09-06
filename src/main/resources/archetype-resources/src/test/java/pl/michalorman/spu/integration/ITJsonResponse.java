package pl.michalorman.spu.integration;

import static junit.framework.Assert.assertEquals;

import java.io.IOException;

import org.apache.commons.httpclient.HttpException;
import org.junit.Test;

public class ITJsonResponse extends BaseITTest {

    /*
     * Given HTTP GET request for *.json should return successful response in JSON format.
     */
    @Test
    public void shouldReturnJsonResponseForJsonExtension() throws HttpException, IOException {
        assertEquals(
                "{\"response\":{\"package\":{\"id\":1,\"position\":{\"latitude\":53.43,\"longitude\":14.529}},\"status\":101}}",
                executeGetByExtension("json", "1"));
        assertEquals(
                "{\"response\":{\"package\":{\"id\":2,\"position\":{\"latitude\":50.47,\"longitude\":16.15}},\"status\":101}}",
                executeGetByExtension("json", "2"));
        assertEquals(
                "{\"response\":{\"package\":{\"id\":3,\"position\":{\"latitude\":50.53,\"longitude\":20.39}},\"status\":101}}",
                executeGetByExtension("json", "3"));
        assertEquals(
                "{\"response\":{\"package\":{\"id\":4,\"position\":{\"latitude\":52.13,\"longitude\":20.57}},\"status\":101}}",
                executeGetByExtension("json", "4"));
        assertEquals(
                "{\"response\":{\"package\":{\"id\":5,\"position\":{\"latitude\":49.57,\"longitude\":18.43}},\"status\":101}}",
                executeGetByExtension("json", "5"));
    }

    /*
     * Give HTTP GET request for *.xml with not existing package identifier should return XML failure response.
     */
    @Test
    public void shouldReturnUnsuccessfulResponseForXmlExtensionAndNotExistinPackage() throws HttpException, IOException {
        assertEquals("{\"response\":{\"package\":null,\"status\":201}}", executeGetByExtension("json", "6"));
    }

    /*
     * Given HTTP GET request with application/json Content-Type should return successful response in JSON format.
     */
    @Test
    public void shouldReturnXmlResponseForXmlContentType() throws HttpException, IOException {
        assertEquals(
                "{\"response\":{\"package\":{\"id\":1,\"position\":{\"latitude\":53.43,\"longitude\":14.529}},\"status\":101}}",
                executeGetByContentType("application/json", "1"));
        assertEquals(
                "{\"response\":{\"package\":{\"id\":2,\"position\":{\"latitude\":50.47,\"longitude\":16.15}},\"status\":101}}",
                executeGetByContentType("application/json", "2"));
        assertEquals(
                "{\"response\":{\"package\":{\"id\":3,\"position\":{\"latitude\":50.53,\"longitude\":20.39}},\"status\":101}}",
                executeGetByContentType("application/json", "3"));
        assertEquals(
                "{\"response\":{\"package\":{\"id\":4,\"position\":{\"latitude\":52.13,\"longitude\":20.57}},\"status\":101}}",
                executeGetByContentType("application/json", "4"));
        assertEquals(
                "{\"response\":{\"package\":{\"id\":5,\"position\":{\"latitude\":49.57,\"longitude\":18.43}},\"status\":101}}",
                executeGetByContentType("application/json", "5"));
    }

    /*
     * Give HTTP GET request with application/json Content-Type with not existing package identifier should return JSON
     * failure response.
     */
    @Test
    public void shouldReturnUnsuccessfulResponseForXmlContentTypeAndNotExistinPackage() throws HttpException,
            IOException {
        assertEquals("{\"response\":{\"package\":null,\"status\":201}}",
                executeGetByContentType("application/json", "6"));
    }

}
