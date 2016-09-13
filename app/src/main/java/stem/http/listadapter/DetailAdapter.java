package stem.http.listadapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import stem.http.R;
import stem.http.model.DetailModel;


/**
 * Created by sandeep on 9/13/16.
 */
public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.MyViewHolder> {


    private ArrayList<DetailModel> list;
    private final Activity activity;
    private int layout;

    public DetailAdapter(Activity activity, ArrayList<DetailModel> list) {
        this.activity = activity;
        this.list = list;

    }

    public void setLayout(int layout) {
        this.layout = layout;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView txtName;
        private final TextView txtMobile;
        private final TextView txtEmail;
        private final ImageView imgProfile;
        private final ProgressBar progressBar;

        public MyViewHolder(View itemView) {
            super(itemView);

            txtName = (TextView) itemView.findViewById(R.id.txtName);
            txtMobile = (TextView) itemView.findViewById(R.id.txtMobile);
            txtEmail = (TextView) itemView.findViewById(R.id.txtEmail);
            imgProfile = (ImageView) itemView.findViewById(R.id.imgProfile);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progressBar);
        }


    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final DetailAdapter.MyViewHolder holder, int position) {

        DetailModel userModel = list.get(position);
        holder.txtName.setText(userModel.getName());
        holder.txtMobile.setText(userModel.getMobile());
        holder.txtEmail.setText(userModel.getEmail());

        Picasso.with(activity)
                .load(userModel.getProfileImageUrl())
                .error(R.drawable.ic_user)      // optional
                .into(holder.imgProfile, new Callback() {
                    @Override
                    public void onError() {
                        holder.progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onSuccess() {
                        holder.progressBar.setVisibility(View.GONE);
                    }

                });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setData(ArrayList<DetailModel> list) {
        this.list = list;
        notifyItemRangeInserted(0, this.list.size());
    }

}
