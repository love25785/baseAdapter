//自訂adapter可以在裡面可自己想要的BUTTON 選項之類的    而simpleAdapter不能
package com.wl.a010803;

import android.support.constraint.solver.ArrayLinkedVariables;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity
{

    boolean[] chks=new boolean[8]; //這是為了等等存取哪些checkbox被打勾用
    ListView lv;
    ArrayList<Map<String,Object>> mylist=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HashMap<String, Object> m1 = new HashMap<>();
        m1.put("city", "台北");
        m1.put("code", "02");
        m1.put("img", R.drawable.taipei);
        mylist.add(m1);
        HashMap<String, Object> m2 = new HashMap<>();
        m2.put("city", "台中");
        m2.put("code", "04");
        m2.put("img", R.drawable.taichung);
        mylist.add(m2);
        HashMap<String, Object> m3 = new HashMap<>();
        m3.put("city", "台南");
        m3.put("code", "06");
        m3.put("img", R.drawable.tainan);
        mylist.add(m3);
        HashMap<String, Object> m4 = new HashMap<>();
        m4.put("city", "高雄");
        m4.put("code", "07");
        m4.put("img", R.drawable.kaoh);
        mylist.add(m4);
        HashMap<String, Object> m5 = new HashMap<>();
        m5.put("city", "台北2");
        m5.put("code", "202");
        m5.put("img", R.drawable.taipei);
        mylist.add(m1);
        HashMap<String, Object> m6 = new HashMap<>();
        m6.put("city", "台中2");
        m6.put("code", "204");
        m6.put("img", R.drawable.taichung);
        mylist.add(m2);
        HashMap<String, Object> m7 = new HashMap<>();
        m7.put("city", "台南2");
        m7.put("code", "206");
        m7.put("img", R.drawable.tainan);
        mylist.add(m3);
        HashMap<String, Object> m8 = new HashMap<>();
        m8.put("city", "高雄2");
        m8.put("code", "207");
        m8.put("img", R.drawable.kaoh);
        mylist.add(m4);

        lv=(ListView)findViewById(R.id.listView);
        myAdapter adapter=new myAdapter();
        lv.setAdapter(adapter);


    }


    class myAdapter extends BaseAdapter
    {
        @Override
        public int getCount()
        {
            return mylist.size(); //改回傳mylist的size
        }

        @Override
        public Object getItem(int i)
        {
            return null;
        }

        @Override
        public long getItemId(int i)
        {
            return 0;
        }

        @Override
        public View getView(final int position, View view, ViewGroup viewGroup) //這裡將position設為final 下面setOnCheckedChangeListener匿名類別的內部類別會用到
        {
            LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
            View v=inflater.inflate(R.layout.layout1,null);

            TextView tv=v.findViewById(R.id.textView);
            tv.setText(mylist.get(position).get("city").toString()); //要物件改成字串

            TextView tv2=v.findViewById(R.id.textView2);
            tv2.setText(mylist.get(position).get("code").toString()); //要物件改成字串

            ImageView img = v.findViewById(R.id.imageView);
            img.setImageResource((Integer) mylist.get(position).get("img"));//雖說是物件,但本題是int，直接轉Int

            CheckBox chk=(CheckBox)v.findViewById(R.id.checkBox);
            chk.setChecked(chks[position]); //重新產生View時  將被記憶的打勾的選項 將他打勾
            chk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
            {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b)
                {
                    chks[position]=b;    //將打勾的選項記憶，且由於內部類別會用到position ，position 要設為final
                }
            });
            /*
                    ↑由於BaseAdapter下可以自製adapter可以放勾選選項等物件，
                    但它們本身是View運作原理是當畫面出現她們時他們會產生，移開時會被回收，
                    所以當勾選某一項後，滑手機畫面將他跑出到畫面外，在滑回畫面內時，已重新產生新的View
                    所以有勾選之類的會消失，所以我們再產生一個變數boolean值來記憶哪些被勾選
                    */

            return v;
        }
    }

    public void click1(View v)
    {
        StringBuilder sb = new StringBuilder();
        for (int i=0;i<chks.length;i++)
        {
            if (chks[i])
            {
                sb.append(mylist.get(i).get("city") + ",");
            }
        }
        Toast.makeText(MainActivity.this, sb.toString(), Toast.LENGTH_LONG).show();
    }
}
