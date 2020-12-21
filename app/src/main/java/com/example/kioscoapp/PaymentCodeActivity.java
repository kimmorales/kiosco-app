package com.example.kioscoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;


import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kioscoapp.Model.ServicesByCarMoneyCenter;
import com.example.kioscoapp.R;
import com.example.kioscoapp.Services.Local.CarLocalService;
import com.example.kioscoapp.Services.Local.CountryLocalService;
import com.example.kioscoapp.Views.InitialScreenFragment;
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

import org.joda.time.DateTime;

import java.io.File;
import java.util.ArrayList;

public class PaymentCodeActivity extends AppCompatActivity {

    WebView webViewService,webViewRecharge;
    Button buttonPrint;
    private static final int REQUEST_CODE_ASK_PERMISSIONS = 123;
    TextView textView7,textView9;
    boolean error;
    public static final String LOGFILE_PATH =
            Environment.getExternalStorageDirectory().getPath().toString()+
                    "/MobileSDKTestApp/logs/";
    private static TGCSMPOS4610Printer device = null;
    private boolean initializationFlag = true;
    Bitmap codigoBarras;
    private String  idBarcodeService = "";
    private String  idBarcodeCharge = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_code);
        String codService = getIntent().getExtras().getString("codService");
        String codRecharge = getIntent().getExtras().getString("codRecharge");
        idBarcodeCharge = getIntent().getExtras().getString("idBarcodeCharge");
        idBarcodeService = getIntent().getExtras().getString("idBarcodeService");
        error=false;

        webViewService=findViewById(R.id.webViewService);
        webViewRecharge=findViewById(R.id.webViewRecharge);
        buttonPrint=findViewById(R.id.buttonReadyPrint);
        textView7 = findViewById(R.id.textView7);
        textView9 = findViewById(R.id.textView9);
        paintCodeBar(webViewService,codService);
        paintCodeBar(webViewRecharge,codRecharge);

        AppContext.getInstance().setContext(this);
        DeviceInfo trace = new DeviceInfo(this);
        trace.printDeviceInformation(); // Capture device hardware and manufacturer information
        initializeDriverLogs();
        initializeUI();

    }

    private void initializeUI() {
        final Button btn_print = (Button) findViewById(R.id.buttonReadyPrint);
        btn_print.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Log.i("DemoApp", "OnClick button pressed");
                String localDeviceName = "POSPrinter:USB";
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
                catch( Exception e )
                {
                    e.printStackTrace();
                    Log.d("DemoApp", "Exception: " + e.getMessage());
                }
            }
        });
    }




    /*
     * This method demonstrates the use of printer methods supported by the TGCSMPOS API to
     * print a sample receipt.
     * @param: logicalDeviceName: logical device name of the target.
     * @effects: prints sample receipt on the device.
     * @return: String result
     */
    private void PrinterReceipt(String logicalDeviceName) {
        DateTime currentDate = new DateTime();
        CarLocalService carLocalService= new CarLocalService(this);
        ArrayList<ServicesByCarMoneyCenter> services= carLocalService.getItemsCar();
        CountryLocalService localService= new CountryLocalService(this);
        String date = "\\x1B|N\\x1B|bC\\x1B|3C\\x1B|cA"+ currentDate.getDayOfMonth() + "/"+
                currentDate.getMonthOfYear()+ "/" + currentDate.getYear() + "\n";
        String title = "\\x1B|N\\x1B|bC\\x1B|3C\\x1B|cAMoney Center\n";
        String subtitle = "\\x1B|cA\\x1B|3C\\x1B|bCCódigo de pago\n";
        double totalAmount = 0;
        System.out.println("PRUEBA");
        System.out.println(title);
        try
        {

            device.printNormal(POS.PTR_S_RECEIPT,date);
            device.printNormal(POS.PTR_S_RECEIPT,title);
            device.printNormal(POS.PTR_S_RECEIPT,subtitle);

            // Use transactionPrint to buffer receipt data until it should be printed.
            device.transactionPrint(POS.PTR_S_RECEIPT,POS.PTR_TP_TRANSACTION);
            device.printNormal(POS.PTR_S_RECEIPT,
                    "\\x1B|bC\\x1B|cAMuestre este código al cajero en la tienda\n\\x1B|bC\\x1B|cApara pagar tus servicios.\n");

            if(idBarcodeService != null){
                device.printNormal(POS.PTR_S_RECEIPT, "\\x1B|N\\x1B|bC\\x1B|3C\\x1B|cAServicios\n");
                device.printBarCode(POS.PTR_S_RECEIPT,
                        idBarcodeService, POS.PTR_BCS_Code128,
                        100, 2, POS.PTR_BC_CENTER, POS.PTR_BC_TEXT_ABOVE);
            }
            if(idBarcodeCharge != null){
                device.printNormal(POS.PTR_S_RECEIPT, "\\x1B|N\\x1B|bC\\x1B|3C\\x1B|cARecargas\n");
                device.printBarCode(POS.PTR_S_RECEIPT,
                        idBarcodeCharge, POS.PTR_BCS_Code128,
                        100, 2, POS.PTR_BC_CENTER, POS.PTR_BC_TEXT_ABOVE);
            }
            device.printNormal(POS.PTR_S_RECEIPT, "\\x1B|N\\x1B|bC\\x1B|3C\\x1B|cA_____________________________\n");
            // barcode
            for (int i=0; i< services.size() ; i++)
            {
                ServicesByCarMoneyCenter service = services.get(i);
                String serviceInfo = "\\x1B|bC\\x1B|cA"+ service.getServiceName() + " "+
                        service.getAccountNumber() + "  " +  localService.getCurrency() + service.getAmount() + "\n";
                totalAmount += Double.parseDouble(service.getAmount());
                device.printNormal(POS.PTR_S_RECEIPT, serviceInfo);
            }
            String total = "\\x1B|bC\\x1B|cA Total: " + localService.getCurrency() + totalAmount;
            device.printNormal(POS.PTR_S_RECEIPT, total );
            device.printNormal(POS.PTR_S_RECEIPT, "\\x1B|N\\x1B|bC\\x1B|3C\\x1B|cA_____________________________\n");

            // Receipt footer.

            // Print Normal
            device.printNormal(POS.PTR_S_RECEIPT, "\\x1B|cA Este tiquete no es una factura\n");
            device.printNormal(POS.PTR_S_RECEIPT, "\\x1B|cA Válido por el día de hoy\n");
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
                codigoBarras =  screenshot(webView);
            }
        }catch (Exception e){

        }

    }
    private static Bitmap screenshot(WebView webView) {
        webView.measure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED),View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        webView.layout(0, 0, webView.getMeasuredWidth(),
                webView.getMeasuredHeight());
        webView.setDrawingCacheEnabled(true);
        webView.buildDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap(webView.getMeasuredWidth(),
                webView.getMeasuredHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        int iHeight = bitmap.getHeight();
        canvas.drawBitmap(bitmap, 0, iHeight, paint);
        webView.draw(canvas);
        return bitmap;
    }
    public  void back(View view){
        finish();
    }
}
