package com.app.gaolonglong.fragmenttabhost;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by donglinghao on 2016-01-28.
 */
public class QiangdanFragment extends Fragment {
    ListView msgList;
    private View mRootView;
    private List<MessageItem> messages=new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null){
            Log.e("666","HomeFragment");
            mRootView = inflater.inflate(R.layout.activity_message_list,container,false);
        }
        ViewGroup parent = (ViewGroup) mRootView.getParent();
        if (parent != null){
            parent.removeView(mRootView);
        }
        initData();
        return mRootView;
    }
    private void initData() {
        messages.clear();
        List<DingDanCaiItem> caimings=new ArrayList<>();
        DingDanCaiItem itemitem=new DingDanCaiItem("西红柿炒蛋","成品","30");
        caimings.add(itemitem);
        DingDanCaiItem itemitem2=new DingDanCaiItem("清炒西蓝花","自由搭配","30");
        caimings.add(itemitem2);
        MessageItem item=new MessageItem("李鹏","五环外","请在30分钟送到",caimings);
        messages.add(item);


        List<DingDanCaiItem> caimings1=new ArrayList<>();
        DingDanCaiItem itemitem31=new DingDanCaiItem("炖牛肉","成品","40");
        caimings1.add(itemitem31);
        DingDanCaiItem itemitem41=new DingDanCaiItem("红烧猪肉","自由搭配","40");
        caimings1.add(itemitem41);
        MessageItem item1=new MessageItem("张震","五环内","请在30分钟送到",caimings1);
        messages.add(item1);

        List<DingDanCaiItem> caimings11=new ArrayList<>();
        DingDanCaiItem itemitem311=new DingDanCaiItem("鱼香肉丝","成品","30");
        caimings11.add(itemitem311);
        DingDanCaiItem itemitem411=new DingDanCaiItem("黄瓜炒鸡蛋","自由搭配","25");
        caimings11.add(itemitem411);
        MessageItem item11=new MessageItem("孙坚","四环外","请在30分钟送到",caimings11);
        messages.add(item11);

        List<DingDanCaiItem> caimings111=new ArrayList<>();
        DingDanCaiItem itemitem3111=new DingDanCaiItem("油炸花生米","成品","20");
        caimings111.add(itemitem3111);
        DingDanCaiItem itemitem4111=new DingDanCaiItem("凉拌黄瓜","自由搭配","20");
        caimings111.add(itemitem4111);
        MessageItem item111=new MessageItem("关兴","四环外","请在30分钟送到",caimings111);
        messages.add(item111);

        List<DingDanCaiItem> caimings1111=new ArrayList<>();
        DingDanCaiItem itemitem31111=new DingDanCaiItem("酸菜鱼","成品","35");
        caimings1111.add(itemitem31111);
        DingDanCaiItem itemitem41111=new DingDanCaiItem("红烧肉","自由搭配","35");
        caimings1111.add(itemitem41111);
        MessageItem item1111=new MessageItem("刘磊","四环","请在30分钟送到",caimings1111);
        messages.add(item1111);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        msgList=(ListView)view.findViewById(R.id.message_list);
        msgList.setAdapter(new MessageAdapter());
    }

    private class MessageAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return messages.size();
        }

        @Override
        public Object getItem(int position) {
            return messages.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Viewholder holder=null;
            if(convertView==null){
                convertView= LayoutInflater.from(getActivity()).inflate(R.layout.message_item,null);
                holder=new Viewholder();
                holder.name=(TextView)convertView.findViewById(R.id.name);
                holder.address=(TextView)convertView.findViewById(R.id.address);
                holder.markdown=(TextView)convertView.findViewById(R.id.mark);
                holder.caimings=(ViewGroup) convertView.findViewById(R.id.caigroup);
                holder.total=(TextView)convertView.findViewById(R.id.total);
                convertView.setTag(holder);
            }else{
                holder=(Viewholder)convertView.getTag();
            }
            holder.name.setText(messages.get(position).name);
            holder.address.setText("地址:"+messages.get(position).address);
            holder.markdown.setText(messages.get(position).markdonw);
            int total=0;
            holder.caimings.removeAllViews();
            for(int i=0;i<messages.get(position).caimings.size();i++){
                View view=LayoutInflater.from(getActivity()).inflate(R.layout.shangjia_list_cai_item,null);
                TextView cainame=(TextView)view.findViewById(R.id.caiming);
                TextView type=(TextView)view.findViewById(R.id.type);
                TextView price=(TextView)view.findViewById(R.id.price);
                cainame.setText(messages.get(position).caimings.get(i).name);
                type.setText("("+messages.get(position).caimings.get(i).type+")");
                price.setText(messages.get(position).caimings.get(i).price);
                total+=Float.valueOf(messages.get(position).caimings.get(i).price);
                holder.caimings.addView(view);
            }
            holder.total.setText("合计:"+total);
            return convertView;
        }
    }

    private class MessageItem {
        public MessageItem(String name,String address,String markdown,List<DingDanCaiItem> array){
            this.name=name;
            this.address=address;
            this.markdonw=markdown;
            this.caimings=array;
        }
        String name;
        String address;
        String markdonw;
        List<DingDanCaiItem> caimings=new ArrayList<>();
    }

    private class Viewholder {
        TextView name;
        TextView address;
        TextView markdown;
        ViewGroup caimings;
        TextView total;
    }

    private class DingDanCaiItem {
        public DingDanCaiItem(String name,String type,String price){
            this.name=name;
            this.type=type;
            this.price=price;
        }
        String name;
        String type;
        String price;
    }

}
