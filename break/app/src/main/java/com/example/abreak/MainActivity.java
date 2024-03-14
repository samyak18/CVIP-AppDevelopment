package com.example.abreak;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Movie> movieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieList = new ArrayList<>();
        movieList.add(new Movie("Baahubali:The beginning", "Baahubali: The Beginning is a 2015 Indian Telugu-language epic action film co-written and directed by S. S. Rajamouli, and produced by Shobu Yarlagadda and Prasad Devineni under Arka Media Works. The film was produced in Tollywood and was filmed in both Telugu and Tamil languages simultaneously. It features Prabhas in a dual role alongside Rana Daggubati, Anushka Shetty, Tamannaah Bhatia, Ramya Krishna, Sathyaraj, and Nassar."));
        movieList.add(new Movie("Bahubali:The conclusion", "Baahubali 2: The Conclusion is a 2017 Indian Telugu-language epic action drama film directed by S. S. Rajamouli, who co-wrote the film with V. Vijayendra Prasad. The film was produced by Shobu Yarlagadda and Prasad Devineni under the banner Arka Media Works. The film was produced in Tollywood, and was filmed in both Telugu and Tamil languages simultaneously. It features Prabhas, Rana Daggubati, Anushka Shetty, Tamannaah Bhatia, Ramya Krishna, Sathyaraj, Nassar, and Subbaraju."));
        movieList.add(new Movie("baaghi", "Baaghi is a 2016 Indian Hindi-language action thriller film directed by Sabbir Khan and produced by Sajid Nadiadwala under his banner Nadiadwala Grandson Entertainment."));
        movieList.add(new Movie("kaabil", "Kaabil is a 2017 Indian Hindi-language romantic action thriller film directed by Sanjay Gupta, written by Vijay Kumar Mishra, produced by Rakesh Roshan under his banner FilmKraft Productions. It stars Hrithik Roshan, Yami Gautam, Ronit Roy and Rohit Roy. The music was composed by Rajesh Roshan. Principal photography of the film began on 30 March 2016."));
        movieList.add(new Movie("super30", "Super 30 is a 2019 Indian Hindi-language biographical drama film produced by Phantom Films, Nadiadwala Grandson Entertainment, Reliance Entertainment and HRX Films at budget of ₹60 crore. Based on the life of mathematics teacher and educator Anand Kumar as well as educational program of the same title, it is directed by Vikas Bahl and stars Hrithik Roshan as Anand Kumar."));
        movieList.add(new Movie("puspha:the rise", "Pushpa: The Rise is a 2021 Indian Telugu-language action drama film written and directed by Sukumar. It stars Allu Arjun as the title character alongside Fahadh Faasil , and Rashmika Mandanna while Jagadeesh Prathap Bandari, Sunil, Raj Tirandasu, Rao Ramesh, Dhananjaya, Anasuya Bharadwaj, Ajay and Ajay Ghosh play supporting roles."));
        movieList.add(new Movie("RRR", "RRR is a 2022 Indian Telugu-language epic period action drama film directed by S. S. Rajamouli, who co-wrote the film with V. Vijayendra Prasad. It was produced by D. V. V. Danayya of DVV Entertainment. The film stars N. T. Rama Rao Jr. and Ram Charan alongside an ensemble cast with Ajay Devgn, Alia Bhatt, Shriya Saran, Samuthirakani, Ray Stevenson, Alison Doody, and Olivia Morris playing supporting roles."));
        movieList.add(new Movie("jawan", "Jawan  is a 2023 Indian Hindi-language action thriller film co-written and directed by Atlee in his Hindi film debut. It is produced by Gauri Khan and Gaurav Verma under Red Chillies Entertainment. The film stars Shah Rukh Khan in a dual role as father and son who team up to rectify corruption in society. Nayanthara, Vijay Sethupathi, Deepika Padukone, Priyamani and Sanya Malhotra appear in supporting roles."));
        movieList.add(new Movie("salaar-part1-caesefire", "Salaar: Part 1 – Ceasefire is a 2023 Indian Telugu-language epic action film written and directed by Prashanth Neel and produced by Vijay Kiragandur. It stars Prabhas and Prithviraj Sukumaran, with a supporting cast that includes Shruti Haasan, Jagapathi Babu, Bobby Simha, Tinnu Anand, Easwari Rao, Sriya Reddy and Ramachandra Raju."));



        ListView listView = findViewById(R.id.movieList);
        ArrayAdapter<Movie> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, movieList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Movie movie = movieList.get(position);
                Intent intent = new Intent(MainActivity.this, MovieDetailsActivity.class);
                intent.putExtra("title", movie.getTitle());
                intent.putExtra("description", movie.getDescription());
                startActivity(intent);
            }
        });
    }
}