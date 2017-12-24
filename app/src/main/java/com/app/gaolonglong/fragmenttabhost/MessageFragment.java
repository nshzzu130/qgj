package com.app.gaolonglong.fragmenttabhost;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by donglinghao on 2016-01-28.
 */
public class MessageFragment extends Fragment {

    private View mRootView;


    private List<CustomeItem> datas=new ArrayList<>();
    private ListView listView;
    TextView total;
    Button makesure;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null){
            Log.e("666","MessageFragment");
            mRootView = inflater.inflate(R.layout.message_fragment,container,false);
        }
        ViewGroup parent = (ViewGroup) mRootView.getParent();
        if (parent != null){
            parent.removeView(mRootView);
        }
        for(int i=0;i<5;i++){
            CustomeItem item=new CustomeItem();
            item.name="张三";
            CaiItem item1=new CaiItem("番茄鸡蛋",10);
            CaiItem item2=new CaiItem("清炒黄瓜",8);
            CaiItem item3=new CaiItem("鱼香肉丝",15);
            CaiItem item4=new CaiItem("酱爆螺丝",10);
            item.cailists.add(item1);
            item.cailists.add(item2);
            item.cailists.add(item3);
            item.cailists.add(item4);
//            item.price=10;
//            item.selected=false;
//            item.count=0;
            datas.add(item);

        }
        return mRootView;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView=(ListView)view.findViewById(R.id.finalproductlist);
        total=(TextView)view.findViewById(R.id.total);
        makesure=(Button)view.findViewById(R.id.makesure);
        makesure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"订单提交成功",Toast.LENGTH_LONG).show();
            }
        });
        listView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return datas.size();
            }

            @Override
            public Object getItem(int position) {
                return datas.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {
                ViewHolder holder=null;
                if(convertView==null){
                    holder=new ViewHolder();
                    convertView=LayoutInflater.from(getActivity()).inflate(R.layout.chefitem,null);
                    holder.name=(TextView)convertView.findViewById(R.id.name);
                    holder.price=(TextView)convertView.findViewById(R.id.price);
                    holder.caigroup=(ViewGroup) convertView.findViewById(R.id.caigroup);
//                    holder.cb=(CheckBox)convertView.findViewById(R.id.cb_selected);
                    convertView.setTag(holder);
                }else{
                    holder=(ViewHolder) convertView.getTag();
                }
                holder.name.setText(datas.get(position).name);
                holder.caigroup.removeAllViews();
                for (int i=0;i<datas.get(position).cailists.size();i++){
                    View caiView=LayoutInflater.from(getActivity()).inflate(R.layout.cai_item,null);
                    CheckBox cb=(CheckBox)caiView.findViewById(R.id.cb_selected);
                    if(datas.get(position).cailists.get(i).selected==true){
                        cb.setSelected(true);
                    }else{
                        cb.setSelected(false);
                    }
                    final int index=i;
                    cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            if(isChecked){
                                datas.get(position).cailists.get(index).selected=true;
                            }else{
                                datas.get(position).cailists.get(index).selected=false;
                            }
                            updateTotalPrice();
                        }
                    });
                    TextView name=(TextView)caiView.findViewById(R.id.caiming);
                    TextView price=(TextView)caiView.findViewById(R.id.price);
                    name.setText(datas.get(position).cailists.get(i).name);
                    price.setText(datas.get(position).cailists.get(i).price+"");
                    holder.caigroup.addView(caiView);
                }

                //holder.price.setText(datas.get(position).price+"/个");
//                if(datas.get(position).selected==true){
//                    holder.cb.setSelected(true);
//                }else{
//                    holder.cb.setSelected(false);
//                }
//                holder.cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                    @Override
//                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                        if(isChecked){
//                            datas.get(position).selected=true;
//                        }else{
//                            datas.get(position).selected=false;
//                        }
//                        updateTotalPrice();
//                    }
//                });
                return convertView;
            }
        });
    }
    private void updateTotalPrice(){
        float totalPrice=0.0f;
        for(int i=0;i<datas.size();i++){
            for(int j=0;j<datas.get(i).cailists.size();j++){
                if(datas.get(i).cailists.get(j).selected==true){
                    totalPrice+=datas.get(i).cailists.get(j).price;
                }
            }

        }
        total.setText("总计："+totalPrice+"元");
    }
    private class CustomeItem {
        String name;
        List<CaiItem> cailists=new ArrayList<>();
//        float price;
//        int count;
//        boolean selected;
    }

    public static class ViewHolder {
        TextView name;
        TextView price;
//        CheckBox cb;
        ViewGroup caigroup;
    }

    private class CaiItem {
        public CaiItem(String name,float price){
            this.name=name;
            this.price=price;
        }
        boolean selected;
        String name;
        float price;
    }
}
