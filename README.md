# ChipRoudCorner-Android
This project is help to create chipset (Roud corner rectangle) ,chipset is nothing but its an item having a roudcorner rectangle design it have two view one is at left with an icon and another one is icon follwed by the chiset title ,(we can delete /not update ,change txt color ,change each item icons ..etc)



        jcenter()
        maven { url "https://jitpack.io" }

  ## gradle library

        compile 'com.github.sreelallalu:ChipRoudCorner-Android:1.0.0'
 
 

## activity_layout--
 
 
       <com.chipsetround.lalism.ChipsInputLayout
        android:id="@+id/chips_input"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="Start typing for chips... "
        android:textColorHint="#757575"
        android:textColor="#212121"
        app:chipDeletable="false"
        app:edittextvisible="false"
       />




  
  ## Items 
  
  
    public class Items extends Chip {
    private int id;
    private String name;
    private String phone;
    private String email;
    private String phoneType;
    private Uri avatarUri;
    private Drawable avatarDr;
    public ChipItems(int id, String name, String phone, String email, String phoneType, Uri avatarUri, Drawable avatarDr) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.phoneType = phoneType;
        this.avatarUri = avatarUri;
        this.avatarDr = avatarDr;
    }

    public ChipItems(int id, String name, String phone, String email) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public ChipItems() {}

    @Nullable
    @Override
    public Integer getId() {
        return id;
    }

    @NonNull
    @Override
    public String getTitle() {
        return name;
    }

    @Nullable
    @Override
    public String getSubtitle() {
        if (phoneType != null && phone != null) {
            return phoneType + ": " + phone;
        }
        return null;
    }

    @Nullable
    @Override
    public Uri getAvatarUri() {
        return avatarUri;
    }

    @Nullable
    @Override
    public Drawable getAvatarDrawable() {
        return avatarDr;
    }

    public void setAvatarUri(Uri uri) {
        this.avatarUri = uri;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(String phoneType) {
        this.phoneType = phoneType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    }


 ## chip_adapter 


    public class ItemChipAdapter extends RecyclerView.Adapter<ItemChipAdapter.Holder> implements ChipChangedObserver {
    private final OnContactClickListener listener;
    private ChipDataSource chipDataSource;
    ItemChipAdapter(OnContactClickListener listener) {
        this.listener = listener;
     
    }
    @Override
    public int getItemCount() {
        return chipDataSource == null ? 0 : chipDataSource.getFilteredChips().size();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.adapter_filtereable_item, parent, false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        Chip chip = chipDataSource.getFilteredChips().get(position);
        holder.text.setText(chip.getTitle());
        holder.subtitle.setText(chip.getSubtitle());
        if (chip.getAvatarUri() != null) {
            holder.image.setImageURI(chip.getAvatarUri());
        } else if (chip.getAvatarDrawable() != null) {
            holder.image.setImageDrawable(chip.getAvatarDrawable());
        } else {
            holder.image.setImageDrawable(null);
        }
    }

    @Override
    public void onChipDataSourceChanged() {

        notifyDataSetChanged();
    }

    void setChipDataSource(ChipDataSource chipDataSource) {
        this.chipDataSource = chipDataSource;

        notifyDataSetChanged();
    }

    interface OnContactClickListener {
        void onContactClicked(ChipItems chip);
    }


    class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView text, subtitle;
        ImageView image;

        Holder(View v) {
            super(v);
            this.text = v.findViewById(R.id.title);
            this.subtitle = v.findViewById(R.id.subtitle);
            this.image = v.findViewById(R.id.image);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            final Chip clickedChip = chipDataSource.getFilteredChip(getAdapterPosition());

        }
    }
}

## Main Activity  ---

    public class MainActivity extends AppCompatActivity implements ItemChipAdapter.OnContactClickListener 
    {
    RecyclerView _recycler;
    ItemChipAdapter contactAdapter;
    private ChipsInputLayout chipsInputLayout;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _recycler = (RecyclerView) findViewById(R.id.recycler);
        this.contactAdapter = new ItemChipAdapter(this);
        RecyclerView recycler = (RecyclerView) findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(contactAdapter);
        chipsInputLayout = (ChipsInputLayout) findViewById(R.id.chips_input);
        submit = (Button) findViewById(R.id.submit);

        setUp();
        setChipSet();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                ChipsAdapter.allList(new ChipsAdapter.SelectedListItems() {
                    @Override
                    public void selected(List<Chip> selected) {
                        for (int i = 0; i < selected.size(); i++) {
                            Log.e("list array", selected.get(i).getTitle());

                        }
                    }
                });


            }
        });
    }

    private void setChipSet() {
        List<Chip> listchip = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            listchip.add(new ChipItems(1 + i, "kidukki " + i, "366663" + i, "email.co" + i));
        }

        this.chipsInputLayout.setSelectedChipList(listchip);

    }

    private void setUp() {

        List<Chip> listchip = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            listchip.add(new ChipItems(1 + i, "jins" + i, "366663" + i, "email.co" + i));

        }

        this.chipsInputLayout.setFilterableChipList(listchip);
    }

    @Override
    public void onContactClicked(ChipItems chip) {

    }
    }







