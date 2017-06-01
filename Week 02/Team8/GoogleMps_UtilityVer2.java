package CSE360;
import java.io.InputStream;
import java.net.URL;
import java.nio.ByteBuffer;

public class GoogleMps_UtilityVer2 {

    private String api_url = "https://maps.googleapis.com/maps/api/staticmap?";
    private String api_key = "AIzaSyCPBkB4FfvXzIYRgbRBmAMxqXLhnaGJVXU";
    private URL url1;
    private byte[] picture = new byte[68108];


    public GoogleMps_UtilityVer2() {

        String center = "center=33.418802,-111.933154&";
        String zoom = "zoom=11&";
        String size = "size=612x612&";
        String scale = "scale=1&";
        String mapType = "maptype=roadmap&";

        api_url = api_url + center + zoom + size + scale + mapType;
        api_url = api_url + "key=" + api_key;

        try {
            url1 = new URL(api_url);
        } catch (java.net.MalformedURLException e) {
            //?
        }

        try {
            //int count = 0;
            byte[] read;

            InputStream inStream;
            inStream = url1.openStream();

            /*
            while(inStream.read() != -1){
                count++;
            }
            System.out.print(count);
            */

            for (int i = 0; i < 68108; i++){
                read = ByteBuffer.allocate(4).putInt(inStream.read()).array();
                picture[i] = read[3];
            }

            inStream.close();
        } catch (java.io.IOException e){
            System.exit(1);
        }
    }

    public byte[] getPicture() {
        return picture;
    }
}