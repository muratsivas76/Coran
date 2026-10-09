import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

  private ListView modeList=null;
  private String [] FILE_OPTIONS=null;
  
    FILE_OPTIONS=new String []
    {
      onlyFile, allDirs, AES, RC2, RC4, ARCFOUR, Blowfish
    };
    
    modeList= (ListView) findViewById (R.id.modeList);
    ArrayAdapter<String> opAdapter = new ArrayAdapter <String> (this, R.layout.custom_list, FILE_OPTIONS);
    modeList.setAdapter(opAdapter);

    modeList.setOnItemClickListener (new AdapterView.OnItemClickListener () {
        public void onItemClick (AdapterView <?> parent, View view, int position, long id)
        {
          if (position==0x0000)
          {
            ONLY_FILE=true;
            status_tv.setText ("  "+onlyFile);
          }
          else if (position==0x0001)
          {
            ONLY_FILE=false;
            status_tv.setText ("  "+allDirs);
          }
          else
          {
            return;
          }
        }
    });
