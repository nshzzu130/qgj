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

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by donglinghao on 2016-01-28.
 */
public class CustomServiceFragment extends Fragment {
    private View mRootView;
    private List<ChefItem> datas=new ArrayList<>();
    private ListView listView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null){
            Log.e("666","MessageFragment");
            mRootView = inflater.inflate(R.layout.customservice_fragment,container,false);
        }
        ViewGroup parent = (ViewGroup) mRootView.getParent();
        if (parent != null){
            parent.removeView(mRootView);
        }
        initDatas();
        return mRootView;
    }

    private void initDatas() {
        datas.clear();
        String[] cainames={"川菜","粤菜","鲁菜","湘菜"};
        ChefItem item=new ChefItem("张师傅",374,234,cainames);
        datas.add(item);
        String[] cainames1={"浙菜","粤菜","鲁菜","湘菜"};
        ChefItem item1=new ChefItem("李师傅",374,234,cainames1);
        datas.add(item1);
        String[] cainames2={"日菜","韩式菜","鲁菜","湘菜"};
        ChefItem item2=new ChefItem("王师傅",374,234,cainames2);
        datas.add(item2);
        String[] cainames3={"川菜","粤菜","鲁菜","湘菜"};
        ChefItem item3=new ChefItem("张师傅",374,234,cainames3);
        datas.add(item3);
        String[] cainames14={"浙菜","粤菜","鲁菜","湘菜"};
        ChefItem item14=new ChefItem("李师傅",374,234,cainames14);
        datas.add(item14);
        String[] cainames21={"日菜","韩式菜","鲁菜","湘菜"};
        ChefItem item21=new ChefItem("王师傅",374,234,cainames21);
        datas.add(item21);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView=(ListView)view.findViewById(R.id.customeservice_list);
        listView.setAdapter(new ChefList());
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private class ChefItem {
        public ChefItem(String name,int good_judge,int service_familys,String[] cailists){
            this.name=name;
            this.good_judge=good_judge;
            this.service_familys=service_familys;
            if(cailists!=null){
                for(String cainame:cailists){
                    this.cailists.add(cainame);
                }
            }
        }
        String name;
        String score;
        int good_judge;
        int service_familys;
        List<String> cailists=new ArrayList<>();
    }

    public static class ViewHolder {
        TextView name;
        TextView goodjudge;
        TextView score;
        TextView service_family;
        ViewGroup caigroup;
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
                holder.goodjudge=(TextView)convertView.findViewById(R.id.goodjudge);
                holder.service_family=(TextView)convertView.findViewById(R.id.service_family);
                holder.caigroup=(ViewGroup) convertView.findViewById(R.id.caigroup);
                convertView.setTag(holder);
            }else{
                holder=(ViewHolder) convertView.getTag();
            }
            holder.name.setText(datas.get(position).name);
            holder.goodjudge.setText(datas.get(position).good_judge+"好评");
            holder.service_family.setText("服务过"+datas.get(position).service_familys+"家庭");
            holder.caigroup.removeAllViews();
            for (int i=0;i<datas.get(position).cailists.size();i++){
                View caiView=LayoutInflater.from(getActivity()).inflate(R.layout.text_item,null);
                TextView name=(TextView)caiView.findViewById(R.id.name);
                name.setPadding(30,0,30,0);
                name.setText(datas.get(position).cailists.get(i));
                holder.caigroup.addView(caiView);
            }
            return convertView;
        }
    }
}
