package com.app.gaolonglong.fragmenttabhost;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by donglinghao on 2016-01-28.
 */
public class HomeFragment extends Fragment {
    private List<CustomeItem> datas=new ArrayList<>();
    private List<String> caileis=new ArrayList<>();
    private View mRootView;
    private ListView listView;
    private ListView caiLeiListView;
    TextView total;
    Button makesure;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null){
            Log.e("666","HomeFragment");
            mRootView = inflater.inflate(R.layout.home_fragment,container,false);
        }
        ViewGroup parent = (ViewGroup) mRootView.getParent();
        if (parent != null){
            parent.removeView(mRootView);
        }
        initDatas();
        return mRootView;
    }
    private void initDatas() {
        caileis.clear();
        datas.clear();

        caileis.add("蔬菜");
        caileis.add("鱼肉");
        caileis.add("海鲜");
        caileis.add("配料");
        String shucai[]={"西红柿","黄瓜","大白菜","土豆","生菜"};
        for(int i=0;i<5;i++){
            CustomeItem item=new CustomeItem(shucai[i],0.4f,0,false);
            datas.add(item);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView=(ListView)view.findViewById(R.id.customedlist);
        caiLeiListView=(ListView)view.findViewById(R.id.caixilist);
        total=(TextView)view.findViewById(R.id.total);
        makesure=(Button)view.findViewById(R.id.makesure);
        makesure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"订单提交成功",Toast.LENGTH_LONG).show();
            }
        });
        listView.setAdapter(new CaiListAdapter());
        caiLeiListView.setAdapter(new CaileiAdapter());
        caiLeiListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int size=parent.getAdapter().getCount();
                for(int i=0;i<size;i++){
                    if(parent!=null&&parent.getChildAt(i)!=null)
                        parent.getChildAt(i).setBackgroundColor(Color.WHITE);
                }
                if(view!=null)
                    view.setBackgroundColor(Color.GRAY);
                datas.clear();
                refreshData(position);
            }
        });
    }


    private void refreshData(int position) {
        if(position==0){
            String shucai[]={"西红柿","黄瓜","大白菜","土豆","生菜"};
            for(int i=0;i<5;i++){
                CustomeItem item=new CustomeItem(shucai[i],0.4f,0,false);
                datas.add(item);
            }
        }else if(position==1){
            String shucai[]={"鲫鱼","猪肉","牛肉","羊肉","黑鱼"};
            for(int i=0;i<5;i++){
                CustomeItem item=new CustomeItem(shucai[i],0.4f,0,false);
                datas.add(item);
            }
        }else if(position==2){
            String shucai[]={"澳洲虾","三门蟹","牡蛎","海螺","生蚝"};
            for(int i=0;i<5;i++){
                CustomeItem item=new CustomeItem(shucai[i],0.4f,0,false);
                datas.add(item);
            }
        }else if(position==3){
            String shucai[]={"生姜","辣椒","八角","花椒","酱油"};
            for(int i=0;i<5;i++){
                CustomeItem item=new CustomeItem(shucai[i],0.4f,0,false);
                datas.add(item);
            }
        }
        listView.setAdapter(new CaiListAdapter());
    }

    private void updateTotalPrice(){
        float totalPrice=0.0f;
        for(int i=0;i<datas.size();i++){
            if(datas.get(i).selected==true){
                totalPrice+=datas.get(i).count*datas.get(i).price;
            }
        }
        total.setText("总计："+totalPrice+"元");
    }
    private class CustomeItem {
        public CustomeItem(String name,float price,int count,boolean selected){
            this.name=name;
            this.price=price;
            this.count=count;
            this.selected=selected;
        }
        String name;
        float price;
        int count;
        boolean selected;
    }

    public static class ViewHolder {
        TextView name;
        TextView price;
        CheckBox cb;
        EditText num;
    }

    private class CaiListAdapter extends BaseAdapter {
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
                convertView=LayoutInflater.from(getActivity()).inflate(R.layout.customeditem,null);
                holder.name=(TextView)convertView.findViewById(R.id.name);
                holder.price=(TextView)convertView.findViewById(R.id.price);
                holder.num=(EditText)convertView.findViewById(R.id.num);
                holder.cb=(CheckBox)convertView.findViewById(R.id.cb_selected);
                convertView.setTag(holder);
            }else{
                holder=(ViewHolder) convertView.getTag();
            }
            holder.name.setText(datas.get(position).name);
            holder.price.setText(datas.get(position).price+"/俩");
            holder.num.setText(datas.get(position).count==0?"":datas.get(position).count+"");
            holder.num.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if(s.toString().isEmpty()){
                        datas.get(position).count=0;
                    }else{
                        datas.get(position).count=Integer.valueOf(s.toString());
                    }
                    updateTotalPrice();
                }
            });
            if(datas.get(position).selected==true){
                holder.cb.setSelected(true);
            }else{
                holder.cb.setSelected(false);
            }
            holder.cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                        datas.get(position).selected=true;
                    }else{
                        datas.get(position).selected=false;
                    }
                    updateTotalPrice();
                }
            });
            return convertView;
        }
    }

    private class CaileiAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return caileis.size();
        }

        @Override
        public Object getItem(int position) {
            return caileis.get(position);
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
                convertView=LayoutInflater.from(getActivity()).inflate(R.layout.text_item,null);
                holder.name=(TextView)convertView.findViewById(R.id.name);
                convertView.setTag(holder);
            }else{
                holder=(ViewHolder) convertView.getTag();
            }
            holder.name.setText(caileis.get(position));
            return convertView;
        }

    }
}
