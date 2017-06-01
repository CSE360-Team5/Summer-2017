package CSE360;
import java.io.InputStream;
import java.net.URL;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class googlemps_utility {

    private String api_url = "https://maps.googleapis.com/maps/api/staticmap?";
    private String api_key = "AIzaSyCPBkB4FfvXzIYRgbRBmAMxqXLhnaGJVXU";
    private URL url;
    private byte[] picture;
    
    public googlemps_utility(String flag) {
    	try {
            String imageUrl = "https://maps.googleapis.com/maps/api/staticmap?" 
                    +"center=40.714728,-73.998672&zoom=11&size=612x612&scale=2&maptype=roadmap";
            String destinationFile = "image.jpg";
            String str = destinationFile;
            URL url = new URL(imageUrl);
            InputStream is = url.openStream();
            OutputStream os = new FileOutputStream(destinationFile);

            byte[] b = new byte[2048];
            int length;

            while ((length = is.read(b)) != -1) {
                os.write(b, 0, length);
            }

            is.close();
            os.close();
        } catch (IOException e) {
            System.exit(1);
        }
    }

    public googlemps_utility() {

        String center = "center=Tempe,AZ,85281&";
        String zoom = "zoom=13&";
        String size = "size=612x612&";

        api_url = api_url + center + zoom + size;
        api_url = api_url + "key=" + api_key;

        try {
            url = new URL(api_url);
        } catch (java.net.MalformedURLException e) {
            //?
        }

        try {

            //variables
            int numBytes = 0;
            InputStream inStream1;
            InputStream inStream2;

            //get streams
            inStream1 = url.openStream();
            inStream2 = url.openStream();

            //get size of the stream
            while (inStream1.read() != -1) {
                numBytes++;
            }

            //store the picture into the byte array
            picture = new byte[numBytes];
            inStream2.read(picture);

            //close resources
            inStream1.close();
            inStream2.close();

        } catch (java.io.IOException e){
            System.exit(1);
        }
    }

    public googlemps_utility(String center, String zoom, String sizew, String sizeh) {

        api_url = api_url + "center=" + center + "&"
                + "zoom=" + zoom + "&" + "size=" +
                sizew + "x" + sizeh + "&";
        api_url = api_url + "key=" + api_key;

        try {
            url = new URL(api_url);
        } catch (java.net.MalformedURLException e) {
            //?
        }

        try {

            //variables
            int numBytes = 0;
            InputStream inStream1;
            InputStream inStream2;

            //get streams
            inStream1 = url.openStream();
            inStream2 = url.openStream();

            //get size of the stream
            while (inStream1.read() != -1) {
                numBytes++;
            }

            //store the picture into the byte array
            picture = new byte[numBytes];
            inStream2.read(picture);

            //close resources
            inStream1.close();
            inStream2.close();

        } catch (java.io.IOException e){
            System.exit(1);
        }
    }

    public byte[] getPicture() {

        return picture;
    }
}