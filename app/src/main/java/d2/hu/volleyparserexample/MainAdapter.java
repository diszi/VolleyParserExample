package d2.hu.volleyparserexample;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainAdapter  extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {

    private List<Employee> list = new ArrayList<>();



    public MainAdapter(List<Employee> array){
        this.list.clear();
        this.list.addAll(array);
        this.notifyDataSetChanged();
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main_row,parent,false);
        return new MainAdapter.MainViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, int position) {
        Employee item = list.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        if (list != null && list.size() > 0) {
            return list.size();
        } else {
            return 0;
        }
    }

    static class MainViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.name)
        TextView employee_name;
        @BindView(R.id.age)
        TextView employee_age;
        @BindView(R.id.mail)
        TextView employee_mail;


        public MainViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

        }

        public void bind(Employee item){
            employee_name.setText(item.getFirstName());
            employee_age.setText(String.valueOf(item.getAge()));
            employee_mail.setText(item.getEmail());
        }
    }
}
