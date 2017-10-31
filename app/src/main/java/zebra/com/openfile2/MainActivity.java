package zebra.com.openfile2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity{ //implements OnPageChangeListener,OnLoadCompleteListener{

    TextView dispText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dispText = (TextView) findViewById(R.id.text_file_data);

        //openPdf();

        Button button = (Button) findViewById(R.id.read_pdf_file);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/PDF/sample.pdf");
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.fromFile(file), "application/pdf");
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
            }
        });
    }

    private void openPdf() {

        //https://stackoverflow.com/questions/10530416/how-to-open-a-pdf-via-intent-from-sd-card
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/PDF/sample.pdf");
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file), "application/pdf");
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
    }

    //READ TEXT FILE
    public void getTextFile(View v) {

        // You can provide the dir location from SD card root
        String data = getTextFileData("/PDF/data.txt");

        // Display the text conetents on a TextView
        dispText.setText(data);

    }

    public String getTextFileData(String fileName) {

        // Get the dir of SD Card
        File sdCardDir = Environment.getExternalStorageDirectory();

        // Get The Text file
        File txtFile = new File(sdCardDir, fileName);

        // Read the file Contents in a StringBuilder Object
        StringBuilder text = new StringBuilder();

        try {

            BufferedReader reader = new BufferedReader(new FileReader(txtFile));

            String line;

            while ((line = reader.readLine()) != null) {
                text.append(line + '\n');
            }
            reader.close();
        } catch (IOException e) {
            Log.e("C2c", "Error occured while reading text file!!");

        }

        return text.toString();

    }
}