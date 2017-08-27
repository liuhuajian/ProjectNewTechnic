package messi.lhj.com.autorefreshtest;

import android.content.Context;
import android.support.v4.text.TextUtilsCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.List;

import messi.lhj.com.autorefreshtest.entity.Picture;

/**
 * Created by messi on 2017/8/27.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

    private List<Student> students;
    private ImageLoader imageLoader;
    private List<Picture> pictures;

    public MyAdapter(Context context){
        imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(context.getApplicationContext()));
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Student student = students.get(position);
        Logger.d("onBindViewHolder-->"+position+"------"+student.toString());
        holder.tvName.setText(student.name);
        holder.tvDes.setText(student.desc);
        if (!TextUtils.isEmpty(student.likePic)){
            imageLoader.displayImage(student.likePic,holder.imagPic);
        }
    }

    @Override
    public int getItemCount() {
        return students ==null ? 0:students.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView tvDes;
        ImageView imagPic;
        public ViewHolder(View view){
            super(view);
            tvName = (TextView) view.findViewById(R.id.item_name);
            tvDes = (TextView) view.findViewById(R.id.item_content);
            imagPic = (ImageView) view.findViewById(R.id.item_pic);
        }
    }

    public void refresh(List<Student> students){
        this.students = students;
        notifyDataSetChanged();
    }

    public void insert2Head(Student student){
        if (students!=null) {
            if (pictures!=null) {
                student.likePic = pictures.get(students.size()).pic;
            }
            students.add(0,student);
            notifyItemInserted(0);
        }
    }

    public void insert2Foot(Student student){
        if (students!=null) {
            if (pictures!=null) {
                student.likePic = pictures.get(students.size()).pic;
            }
            students.add(students.size(),student);
            notifyItemInserted(students.size());
        }
    }

    public void updatePic(List<Picture> pictures){
        this.pictures = pictures;
        for (int i=0;i<students.size();i++){
            Student student = students.get(i);
            student.likePic = pictures.get(i).pic;
        }
        notifyDataSetChanged();
    }
}
