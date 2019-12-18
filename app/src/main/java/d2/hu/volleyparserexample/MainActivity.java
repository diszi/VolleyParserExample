package d2.hu.volleyparserexample;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private RequestQueue mQueue;
    private Employee employee_obj;
    private List<Employee> employeeList=new ArrayList<>();
    private MainAdapter adapter;

//    @BindView(R.id.recyclerView_list)
//    RecyclerView recyclerView_list;

    @BindView(R.id.text_view_result)
    TextView mTextViewResult;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mQueue = Volley.newRequestQueue(this);

        //this.setupRecyclerView();

    }

    @OnClick(R.id.button_parse)
    public void onClickOnBtn(){
        jsonParse();
    }

    public void jsonParse(){
        String url = "https://api.themoviedb.org/3/movie/550?api_key=d243e02fe7382c503c90edea8d0dbc21";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("production_companies");
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject employee = jsonArray.getJSONObject(i);
                        employee_obj = new Employee();
                        employee_obj.setFirstName(employee.getString("name"));
                        employee_obj.setAge(employee.getInt("id"));
                        employee_obj.setEmail(employee.getString("origin_country"));
                       // employeeList.add(employee_obj);


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mQueue.add(request);

    }


//    public void setupRecyclerView() {
//        Context context = getApplicationContext();
//        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
//        this.adapter = new MainAdapter(employeeList);
//        this.recyclerView_list.setLayoutManager(layoutManager);
//        this.recyclerView_list.setAdapter(this.adapter);
//    }
}
