package com.app.gaolonglong.fragmenttabhost;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by donglinghao on 2016-01-28.
 */
public class MessageFragment extends Fragment {
    private View mRootView;
    private List<CustomeItem> datas=new ArrayList<>();
    private List<String> caixinames=new ArrayList<>();
    private ListView listView;
    private ListView caixiListView;
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
        initDatas();
        return mRootView;
    }

    private void initDatas() {
        caixinames.clear();
        datas.clear();

        caixinames.add("川菜");
        caixinames.add("粤菜");
        caixinames.add("鲁菜");
        caixinames.add("湘菜");
        caixinames.add("浙菜");
        caixinames.add("日菜");
        caixinames.add("韩式菜");

        for(int i=0;i<5;i++){
            CustomeItem item=new CustomeItem();
            item.name="张师傅";
            CaiItem item1=new CaiItem("雪花鸡淖",10);
            CaiItem item2=new CaiItem("火爆腰花",8);
            CaiItem item3=new CaiItem("酸辣臊子蹄筋",15);
            CaiItem item4=new CaiItem("炝黄瓜",10);
            item.cailists.add(item1);
            item.cailists.add(item2);
            item.cailists.add(item3);
            item.cailists.add(item4);
            datas.add(item);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView=(ListView)view.findViewById(R.id.finalproductlist);
        caixiListView=(ListView)view.findViewById(R.id.caixilist);
        total=(TextView)view.findViewById(R.id.total);
        makesure=(Button)view.findViewById(R.id.makesure);
        makesure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"订单提交成功",Toast.LENGTH_LONG).show();
            }
        });
        listView.setAdapter(new ChefList());
        caixiListView.setAdapter(new CaixiAdapter());
        caixiListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
            for(int i=0;i<5;i++){
                CustomeItem item=new CustomeItem();
                item.name="张师傅";
                CaiItem item1=new CaiItem("雪花鸡淖",10);
                CaiItem item2=new CaiItem("火爆腰花",8);
                CaiItem item3=new CaiItem("酸辣臊子蹄筋",15);
                CaiItem item4=new CaiItem("炝黄瓜",10);
                item.cailists.add(item1);
                item.cailists.add(item2);
                item.cailists.add(item3);
                item.cailists.add(item4);
                datas.add(item);
            }
        }else if(position==1){
            for(int i=0;i<5;i++){
                CustomeItem item=new CustomeItem();
                item.name="李师傅";
                CaiItem item1=new CaiItem("酿豆腐",10);
                CaiItem item2=new CaiItem("梅菜扣肉",8);
                CaiItem item3=new CaiItem("红糟排骨",15);
                CaiItem item4=new CaiItem("客家盐焗鸡",10);
                item.cailists.add(item1);
                item.cailists.add(item2);
                item.cailists.add(item3);
                item.cailists.add(item4);
                datas.add(item);
            }
        }else if(position==2){
            for(int i=0;i<5;i++){
                CustomeItem item=new CustomeItem();
                item.name="王师傅";
                CaiItem item1=new CaiItem("拨丝山药",10);
                CaiItem item2=new CaiItem("糖醋黄河鲤鱼",8);
                CaiItem item3=new CaiItem("九转大肠",15);
                CaiItem item4=new CaiItem("爆炒腰花溜璃核桃仁 ",10);
                item.cailists.add(item1);
                item.cailists.add(item2);
                item.cailists.add(item3);
                item.cailists.add(item4);
                datas.add(item);
            }
        }else if(position==3){
            for(int i=0;i<5;i++){
                CustomeItem item=new CustomeItem();
                item.name="蒋师傅";
                CaiItem item1=new CaiItem("一品茄子",10);
                CaiItem item2=new CaiItem("双椒辣子鸡",8);
                CaiItem item3=new CaiItem("干锅大片土豆",15);
                CaiItem item4=new CaiItem("剁椒丝瓜炒鸡蛋 ",10);
                item.cailists.add(item1);
                item.cailists.add(item2);
                item.cailists.add(item3);
                item.cailists.add(item4);
                datas.add(item);
            }
        }else if(position==4){
            for(int i=0;i<5;i++){
                CustomeItem item=new CustomeItem();
                item.name="康师傅";
                CaiItem item1=new CaiItem("牡蛎跑蛋",10);
                CaiItem item2=new CaiItem("蜜汁灌藕",8);
                CaiItem item3=new CaiItem("西湖莼菜汤",15);
                CaiItem item4=new CaiItem("龙井虾仁",10);
                item.cailists.add(item1);
                item.cailists.add(item2);
                item.cailists.add(item3);
                item.cailists.add(item4);
                datas.add(item);
            }
        }else if(position==5){
            for(int i=0;i<5;i++){
                CustomeItem item=new CustomeItem();
                item.name="马师傅";
                CaiItem item1=new CaiItem("鱼生",10);
                CaiItem item2=new CaiItem("大麦茶",8);
                CaiItem item3=new CaiItem("寿司",15);
                CaiItem item4=new CaiItem("虾仁",10);
                item.cailists.add(item1);
                item.cailists.add(item2);
                item.cailists.add(item3);
                item.cailists.add(item4);
                datas.add(item);
            }
        }
        else if(position==6){
            for(int i=0;i<5;i++){
                CustomeItem item=new CustomeItem();
                item.name="牛师傅";
                CaiItem item1=new CaiItem("炖猪蹄",10);
                CaiItem item2=new CaiItem("大盘鸡",8);
                CaiItem item3=new CaiItem("土豆汤",15);
                CaiItem item4=new CaiItem("泡菜",10);
                item.cailists.add(item1);
                item.cailists.add(item2);
                item.cailists.add(item3);
                item.cailists.add(item4);
                datas.add(item);
            }
        }
        listView.setAdapter(new ChefList());
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

    @Override
    public void onResume() {
        super.onResume();
    }

    private class CustomeItem {
        String name;
        List<CaiItem> cailists=new ArrayList<>();
    }

    public static class ViewHolder {
        TextView name;
        TextView price;
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

    private class CaixiAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return caixinames.size();
        }

        @Override
        public Object getItem(int position) {
            return caixinames.get(position);
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
            holder.name.setText(caixinames.get(position));
            return convertView;
        }
    }

    private class ChefList extends BaseAdapter {
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
            return convertView;
        }
    }
}
