package until;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StringUtil {
    public static boolean isEmpty(String searchName){
        return searchName==null||"".equals(searchName)?false:true;
    }
}
