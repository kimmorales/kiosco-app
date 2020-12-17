package com.example.kioscoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;


import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import com.toshiba.tgcsapi.AppContext;
import com.toshiba.tgcsapi.POS;
import com.toshiba.tgcsapi.POSDataEvent;
import com.toshiba.tgcsapi.POSErrorEvent;
import com.toshiba.tgcsapi.POSEvent;
import com.toshiba.tgcsapi.POSEventHandler;
import com.toshiba.tgcsapi.POSOutputCompleteEvent;
import com.toshiba.tgcsapi.POSStatusUpdateEvent;
import com.toshiba.tgcsapi.TGCSMPOS4610Printer;
import com.toshiba.tgcsapi.TGCSMPOSException;
import com.toshiba.tgcsapi.TGCSMPOSLogging;
import com.toshiba.util.DeviceInfo;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.File;

public class PaymentCodeActivity extends AppCompatActivity {

    WebView webViewService,webViewRecharge;
    Button buttonPrint;
    boolean error;
    public static final String LOGFILE_PATH =
            Environment.getExternalStorageDirectory().getPath().toString()+
                    "/MobileSDKTestApp/logs/";
    private static TGCSMPOS4610Printer device = null;
    private boolean initializationFlag = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_code);
        String codService = getIntent().getExtras().getString("codService");
        String codRecharge = getIntent().getExtras().getString("codRecharge");
        error=false;

        webViewService=findViewById(R.id.webViewService);
        webViewRecharge=findViewById(R.id.webViewRecharge);
        buttonPrint=findViewById(R.id.buttonReadyPrint);

        paintCodeBar(webViewService,codService);
        paintCodeBar(webViewRecharge,codRecharge);

        DeviceInfo trace = new DeviceInfo(this);
        trace.printDeviceInformation(); // Capture device hardware and manufacturer information
        initializeDriverLogs();

        buttonPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                print();
            }
        });
    }

    private void  print(){
        Log.i("DemoApp", "OnClick button pressed");
        String localDeviceName = "POSPrinter:USB";
        error=false;
        try
        {
            if (initializationFlag == true){
                device = new TGCSMPOS4610Printer();
                // add POSEventHandlers. This allows to receive notifications for:
                // Paper status among other notifications.
                device.addPOSEventListener(new POSEventHandler() {
                    /**
                     * There are additional event handlers for Data, Errors and OutputComple
                     * Events. Please refer to UPOS standard for additional information on
                     * how to use them:
                     *  - https://www.omg.org/cgi-bin/doc?retail/2017-07-32
                     */
                    @Override
                    public void handleEvent(POSEvent posEvent) {

                    }

                    @Override
                    public void handleDataEvent(POSDataEvent posDataEvent) {

                    }

                    @Override
                    public void handleErrorEvent(POSErrorEvent posErrorEvent) {

                    }

                    @Override
                    public void handleOutputCompleteEvent(POSOutputCompleteEvent posOutputCompleteEvent) {

                    }

                    /**
                     * A StatusUpdateEvent(SUE) is an object that represent a physical change
                     * in the device. The following are examples of SUE: cover position, paper
                     * status, device power.
                     *
                     * Implement "POSEventHandler" interface and Use this method to process
                     * StatusUpdateEvents.
                     *
                     * @param sue: StatusUpdateEvent notification/object.
                     */
                    @Override
                    public void handleStatusUpdateEvent(POSStatusUpdateEvent sue) {

                        switch(sue.getStatus())
                        {
                            /*
                             * StatusUpdateEvents for the covers.
                             */
                            case POS.PTR_SUE_COVER_OPEN:
                                Log.i("STATUS", "PTR_SUE_COVER_OPEN");
                                break;
                            case POS.PTR_SUE_COVER_OK:
                                Log.i("STATUS", "PTR_SUE_COVER_OK");
                                break;
                            case POS.PTR_SUE_REC_COVER_OPEN:
                                Log.i("STATUS", "PTR_SUE_REC_COVER_OPEN");
                                break;
                            case POS.PTR_SUE_REC_COVER_OK:
                                Log.i("STATUS", "PTR_SUE_REC_COVER_OK");
                                break;
                            /*
                             * StatusUpdateEvents for the paper.
                             * REC stands for "Receipt" and that is the thermal station.
                             * SLP stands for "Slip" and that is the impact station.
                             */
                            case POS.PTR_SUE_REC_EMPTY:
                                Log.i("STATUS", "PTR_SUE_REC_EMPTY");
                                break;
                            case POS.PTR_SUE_REC_PAPEROK:
                                Log.i("STATUS", "PTR_SUE_REC_PAPEROK");
                                break;
                            case POS.PTR_SUE_SLP_EMPTY:
                                Log.i("STATUS", "PTR_SUE_SLP_EMPTY");
                                break;
                            case POS.PTR_SUE_SLP_PAPEROK:
                                Log.i("STATUS", "PTR_SUE_SLP_PAPEROK");
                                break;
                            default:
                                // there are other SUEs.
                                // Identifier of symbolic constants for all of them follow
                                // the pattern: PTR_SUE_*
                                Log.i("STATUS", "Status update value : " + sue.getStatus());
                        }

                    }
                });

                
                // open the device
                device.open(localDeviceName);

                // claim the device for exclusive access; 3000ms timeout
                device.claim(3000);

                // enable the device
                device.setDeviceEnabled(true);

                initializationFlag = false;

            }
            System.out.println("***DEVICE PAPER: "+device.getRecEmpty());
            System.out.println("***DEVICE COVER: "+device.getCoverOpen());

            PrinterReceipt(localDeviceName);
        }
        catch (NullPointerException e){
            if(!error){
                Toast.makeText(this,"Error al imprimir: Asegurese que la impresora este conectada",Toast.LENGTH_LONG).show();
                error=true;
            }
        }
        catch( Exception e )
        {

            e.printStackTrace();
            Log.d("DemoApp", "Exception: " + e.getMessage());
        }catch (UnsatisfiedLinkError err){
            Toast.makeText(this,"Error al imprimir: Asegurese que la impresora este conectada",Toast.LENGTH_LONG).show();
        }
    }


    /*
     * This method demonstrates the use of printer methods supported by the TGCSMPOS API to
     * print a sample receipt.
     * @param: logicalDeviceName: logical device name of the target.
     * @effects: prints sample receipt on the device.
     * @return: String result
     */
    private void PrinterReceipt(String logicalDeviceName)
    {
        // Receipt content
        String content = "\\x1B|N\\x1B|bC\\x1B|3C\\x1B|cATICKET STORE\n";
        content +=       "\\x1B|N\\x1B|cAMMM/DD/YY  HH:MM\n\n";
        content +=       "\\x1B|cA\\x1B|bCStore Number:\\x1B|N 8888\n";
        content +=       "\\x1B|cA\\x1B|bCSeller:\\x1B|N Your Name\n\n";
        content +=       "\\x1B|cAItem 1 .................... $20\n";
        content +=       "\\x1B|cAItem 2 .................... $30\n";
        content +=       "\\x1B|cAItem 3 .................... $40\n";
        content +=       "\\x1B|cAItem 4 .................... $50\n";
        content +=       "\\x1B|cAItem 5 .................... $60\n";
        content +=       "\\x1B|cAItem 6 .................... $70\n";
        content +=       "\\x1B|cAItem 7 .................... $80\n";
        content +=       "\n";
        content +=       "\\x1B|cA\\x1B|3C\\x1B|bCAmount .................... $350\n";
        content +=       "\\x1B|cA\\x1B|NCreditCard (####-####-####-####)\n";
        content +=       "\\x1B|cATAXES INCLUDED\n\n";

        /* https://developer.android.com/guide/topics/resources/providing-resources#ResourcesFromCode */
        Bitmap logo = BitmapFactory.decodeResource(getResources(), R.raw.sqtoshiba_gif);
        Bitmap logoMem = BitmapFactory.decodeResource(getResources(), R.raw.sale);

        try
        {
            // Use transactionPrint to buffer receipt data until it should be printed.
            device.transactionPrint(POS.PTR_S_RECEIPT,POS.PTR_TP_TRANSACTION);

            // logo
            device.printBitmap(POS.PTR_S_RECEIPT, logo, POS.PTR_BM_ASIS, POS.PTR_BC_CENTER);

            // content
            device.printNormal(POS.PTR_S_RECEIPT, content);

            // barcode
            device.printBarCode(POS.PTR_S_RECEIPT, "0123456789", POS.PTR_BCS_Code39, 100, 2, POS.PTR_BC_CENTER, POS.PTR_BC_TEXT_ABOVE);

            // Receipt footer.
            device.printNormal(POS.PTR_S_RECEIPT, "\\x1B|bC\\x1B|cAThis ticket is needed for \n\\x1B|bC\\x1B|cAany return or exchange\n\n");

            // Print Memory bitmap
            TGCSMPOS4610Printer.ImageDimensions dimen = new TGCSMPOS4610Printer.ImageDimensions();
            byte [] data = device.loadBitmap(POS.PTR_S_RECEIPT, logoMem, POS.PTR_BM_ASIS, dimen);

            device.printMemoryBitmap(POS.PTR_S_RECEIPT, data, POS.PTR_BMT_GIF, POS.PTR_BM_ASIS, POS.PTR_BC_CENTER);

            // Print Normal
            device.printNormal(POS.PTR_S_RECEIPT, "\\x1B|cA THANK YOU FOR YOUR BUSINESS\n\n");

            // Paper cut
            device.printNormal(POS.PTR_S_RECEIPT, "\\x1B|fP");

            // Finish transactionPrint to actually print receipt.
            device.transactionPrint(POS.PTR_S_RECEIPT,POS.PTR_TP_NORMAL);

        }
        catch (TGCSMPOSException mpos_e)
        {
            mpos_e.printStackTrace();
            Log.d("DemoApp", "Exception: " + mpos_e.getMessage());
        }
        catch (RuntimeException e)
        {
            e.printStackTrace();
            Log.d("DemoApp", "Exception: " + e.getMessage());
        }
    }

    /**
     * initializing the Driver log mechanism
     */
    private void initializeDriverLogs()
    {
        String logFilePath = LOGFILE_PATH;
        try {
            createDirectoryIfNotExists(logFilePath);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        TGCSMPOSLogging.setLogLevel(7); // Driver log level definition

        // Define log file path
        TGCSMPOSLogging.setLogFilePath(logFilePath);
    }


    /**
     * This function will prepare the mobile device to store Mobile SDK tracing.
     *
     * @param path: Path where log file should be generated.
     * @throws Exception
     */
    private void createDirectoryIfNotExists(String path) throws Exception
    {
        File f = new File(path);

        if (f != null && !f.exists())
        {
            if(!f.mkdirs())
            {
                throw new Exception("Unable to create directory");
            }
        }
    }


    private void paintCodeBar(WebView webView,String base64){
        try{
            String textHtml="<div width='100%'><img style='display:block;margin:auto' src='"+base64+"'></img></div>";
            if(!base64.isEmpty()){
                webView.loadData(textHtml,"text/html","UTF-8");
            }
        }catch (Exception e){

        }

    }

    public  void back(View view){
        finish();
    }
}
