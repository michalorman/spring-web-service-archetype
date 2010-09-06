package pl.michalorman.spu.integration;

import static java.lang.String.format;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.junit.BeforeClass;

/**
 * Use this class as base class for integration tests.
 * 
 * <p>Note that according to filesafe plugin requirements the integration test class names
 * must be prefixed with IT and should not contain Test suffix (or will be executed by surefire
 * plugin during unit tests).
 * 
 * @author Michał Orman
 */
public abstract class BaseITTest {

    private static String targetUrl;

    @BeforeClass
    public static void setUp() {
        targetUrl = System.getProperty("${groupId}.integration.url");
    }

    protected String executeGetByExtension(String extension, String packageId) throws HttpException, IOException {
        return execute(format("%s/track.%s?packageId=%s", targetUrl, extension, packageId));
    }

    protected String executeGetByContentType(String contentType, String packageId) throws HttpException, IOException {
        return execute(format("%s/track?packageId=%s", targetUrl, packageId), contentType);
    }

    private String execute(String url) throws HttpException, IOException {
        return execute(url, null);
    }

    protected String execute(String url, String contentType) throws HttpException, IOException {
        HttpClient client = new HttpClient();
        GetMethod get = new GetMethod(url);
        if (contentType != null) {
            get.addRequestHeader("Accept", contentType);
        }
        client.executeMethod(get);
        return readResponse(get.getResponseBodyAsStream());
    }

    private String readResponse(InputStream input) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        StringBuilder builder = new StringBuilder();

        String line = null;

        while ((line = reader.readLine()) != null) {
            builder.append(line);
        }

        return builder.toString();
    }

}
