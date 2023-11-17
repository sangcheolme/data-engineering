import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class App1 {

    public static void main(String[] args) throws IOException {
        BufferedReader br
                = new BufferedReader(new FileReader("/Users/sangcheol/Desktop/Email-EuAll.txt"));

        List<Email> emails = new ArrayList<>();

        String line = null;
        while (true) {
            line = br.readLine();
            if (line == null)
                break;
            if (line.startsWith("#"))
                continue;

            String[] split = line.split("\t");
            int from = Integer.parseInt(split[0]);
            int to = Integer.parseInt(split[1]);

            emails.add(new Email(from, to));
        }

        br.close();

        for (Email email : emails) {
            System.out.println(email);
        }
    }
}
