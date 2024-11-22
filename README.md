Dưới đây là một ví dụ đầy đủ về cách sử dụng `GridLayoutManager` trong `RecyclerView` để hiển thị các mục theo dạng lưới.

---

### **1. Cấu Trúc Dự Án**

- **activity_main.xml**: Chứa `RecyclerView`.
- **item_view.xml**: Giao diện của từng item trong lưới.
- **MainActivity.java**: Quản lý RecyclerView và hiển thị danh sách.
- **MyAdapter.java**: Adapter để liên kết dữ liệu với RecyclerView.

---

### **2. File XML**

### **a. activity_main.xml**

```xml
xml
Sao chép mã
<androidx.constraintlayout.widget.ConstraintLayout 
xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <androidx.recyclerview.widget.RecyclerView
        android:id="@+id/recyclerView"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:padding="16dp"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent" />

</androidx.constraintlayout.widget.ConstraintLayout>

```

---

### **b. item_view.xml**

```xml
xml
Sao chép mã
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="vertical"
    android:gravity="center"
    android:padding="8dp">

    <ImageView
        android:id="@+id/itemImage"
        android:layout_width="80dp"
        android:layout_height="80dp"
        android:src="@drawable/ic_launcher_foreground"
        android:contentDescription="@string/app_name"
        android:layout_gravity="center" />

    <TextView
        android:id="@+id/itemText"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Item"
        android:textSize="16sp"
        android:layout_marginTop="8dp"
        android:gravity="center" />
</LinearLayout>

```

---

### **3. File Java**

### **a. MainActivity.java**

```java
java
Sao chép mã
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Dữ liệu mẫu
        List<String> itemList = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            itemList.add("Item " + i);
        }

        // Thiết lập RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3); // 3 cột
        recyclerView.setLayoutManager(gridLayoutManager);
        MyAdapter adapter = new MyAdapter(itemList);
        recyclerView.setAdapter(adapter);
    }
}

```

---

### **b. MyAdapter.java**

```java
java
Sao chép mã
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<String> dataList;

    public MyAdapter(List<String> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String item = dataList.get(position);
        holder.textView.setText(item);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.itemImage);
            textView = itemView.findViewById(R.id.itemText);
        }
    }
}

```

---

### **4. Kết Quả**

- RecyclerView hiển thị danh sách các item theo dạng lưới, với mỗi hàng có 3 cột.
- Mỗi item bao gồm một hình ảnh và một tên.

---

### **5. Mở Rộng**

- **Hiển thị lưới ngang**:
Thay đổi `GridLayoutManager` để hiển thị theo chiều ngang:
    
    ```java
    java
    Sao chép mã
    GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3, GridLayoutManager.HORIZONTAL, false);
    recyclerView.setLayoutManager(gridLayoutManager);
    
    ```
    
- **Kích thước item tùy chỉnh**:
Sử dụng `SpanSizeLookup` để thay đổi số cột của một item cụ thể:
    
    ```java
    java
    Sao chép mã
    gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
        @Override
        public int getSpanSize(int position) {
            return position % 5 == 0 ? 3 : 1; // Item đầu tiên chiếm 3 cột, các item còn lại chiếm 1 cột
        }
    });
    
    ```
    

---

### **Lưu Ý**

1. `GridLayoutManager` phù hợp để hiển thị dữ liệu như danh sách ảnh, sản phẩm, hoặc các mục giao diện dạng lưới.
2. Điều chỉnh số cột phù hợp với màn hình bằng cách tính toán số cột động:
    
    ```java
    java
    Sao chép mã
    int spanCount = getResources().getDisplayMetrics().widthPixels / 200; // Ví dụ: mỗi item có chiều rộng 200px
    GridLayoutManager gridLayoutManager = new GridLayoutManager(this, spanCount);
    recyclerView.setLayoutManager(gridLayoutManager);
    
    ```
    

Nếu bạn muốn thêm tính năng cụ thể, hãy cho tôi biết để tùy chỉnh thêm!
