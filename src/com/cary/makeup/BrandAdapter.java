package com.cary.makeup;

import java.util.List;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.SectionIndexer;
import android.widget.TextView;

public class BrandAdapter extends BaseAdapter implements SectionIndexer{

	private List<String> list;
	private Context context;
	
	
	public BrandAdapter(Context context) {
		this.context=context;
		list=Utils.getBrandList();
	}
	
	
	
	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder view=null;
		if(convertView==null) {
			convertView=LayoutInflater.from(context).inflate(R.layout.adapter_item, null);
			view = new ViewHolder();
			view.iv=(ImageView)convertView.findViewById(R.id.brand_img);
			view.tv=(TextView)convertView.findViewById(R.id.brand_name);
			view.py=(TextView)convertView.findViewById(R.id.py);
			convertView.setTag(view);
		}else {
			view=(ViewHolder)convertView.getTag();
		}
		String name=list.get(position);
		String catalog = converterToFirstSpell(name).substring(0, 1);
		if(position==0) {
			view.py.setVisibility(View.VISIBLE);
			view.py.setText(catalog);
		}else {
			String tempName=list.get(position-1);
			String lastCatalog = converterToFirstSpell(tempName).substring(0, 1);
			if(catalog.equals(lastCatalog)){
				view.py.setVisibility(View.GONE);
			}else{
				view.py.setVisibility(View.VISIBLE);
				view.py.setText(catalog);
			}
		}
		view.iv.setBackgroundResource(R.drawable.default_avatar);
		view.tv.setText(name);
		return convertView;
	}

	@Override
	public Object[] getSections() {
		return null;
	}

	@Override
	public int getPositionForSection(int section) {
		for (int i = 0; i < list.size(); i++) {  
            String l = converterToFirstSpell(list.get(i)).substring(0, 1);  
            char firstChar = l.toUpperCase().charAt(0);  
            if (firstChar == section) {  
                return i;  
            }  
        }
		return -1;
	}

	@Override
	public int getSectionForPosition(int position) {
		return 0;
	}

	
	class ViewHolder {
		TextView py;
		ImageView iv;
		TextView tv;
	}
	
    /**  
     * 汉字转换位汉语拼音首字母，英文字符不变  
     * @param chines 汉字  
     * @return 拼音  
     */     
    public static String converterToFirstSpell(String chines){             
         String pinyinName = "";      
        char[] nameChar = chines.toCharArray();      
         HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();      
         defaultFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);      
         defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);      
        for (int i = 0; i < nameChar.length; i++) {      
            if (nameChar[i] > 128) {      
                try {      
                     pinyinName += PinyinHelper.toHanyuPinyinStringArray(nameChar[i], defaultFormat)[0].charAt(0);      
                 } catch (BadHanyuPinyinOutputFormatCombination e) {      
                     e.printStackTrace();      
                 }      
             }else{      
                 pinyinName += nameChar[i];      
             }      
         }      
        return pinyinName;      
     } 
}
