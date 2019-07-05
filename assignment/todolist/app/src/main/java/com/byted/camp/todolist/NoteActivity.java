package com.byted.camp.todolist;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatRadioButton;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.byted.camp.todolist.beans.Priority;
import com.byted.camp.todolist.beans.State;
import com.byted.camp.todolist.db.TodoDbHelper;

public class NoteActivity extends AppCompatActivity {

    private EditText editText;
    private Button addBtn;
    private SQLiteDatabase database;
    private TodoDbHelper dbHelper;
    private RadioButton lowRadio;
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //TODO: 修改xml布局文件
        setContentView(R.layout.activity_note);
        setTitle(R.string.take_a_note);

        editText = findViewById(R.id.edit_text);
        editText.setFocusable(true);
        editText.requestFocus();

        this.dbHelper = new TodoDbHelper(this);
        this.database = this.dbHelper.getWritableDatabase();

        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputManager != null) {
            inputManager.showSoftInput(editText, 0);
        }

        //TODO: xml
        radioGroup = ((RadioGroup) findViewById(R.id.priority_list));
        lowRadio = ((RadioButton) findViewById(R.id.low_button));
        lowRadio.setChecked(true);

        addBtn = findViewById(R.id.btn_add);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence content = editText.getText();

                if (TextUtils.isEmpty(content)) {
                    Toast.makeText(NoteActivity.this,
                            "No content to add", Toast.LENGTH_SHORT).show();
                    return;
                }
                //TODO: priority

                boolean succeed = false;
                switch (radioGroup.getCheckedRadioButtonId()) {
                    case R.id.high_button:
                        succeed = saveNote2Database(content.toString().trim(), new Priority(2));
                        Log.e("XJP", "onClick:check_id = high " );
                        break;
                    case R.id.normal_button:
                        succeed = saveNote2Database(content.toString().trim(), new Priority(1));
                        Log.e("XJP", "onClick:check_id = normal " );
                        break;
                    case R.id.low_button:
                        succeed = saveNote2Database(content.toString().trim(), new Priority(0));
                        Log.e("XJP", "onClick:check_id = low " );
                        break;
                    default:
                        break;
                }
                if (succeed) {
                    Toast.makeText(NoteActivity.this,
                            "Note added", Toast.LENGTH_SHORT).show();
                    setResult(Activity.RESULT_OK);
                } else {
                    Toast.makeText(NoteActivity.this,
                            "Error", Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
        this.database.close();
        this.database = null;
        this.dbHelper.close();
        this.dbHelper = null;
    }

    private boolean saveNote2Database(String paramString, Priority paramPriority) {
        // TODO 插入一条新数据，返回是否插入成功
        boolean bool = false;
        if (this.database != null) {
            if (TextUtils.isEmpty(paramString)) {
                return false;
            }
            ContentValues values = new ContentValues();
            values.put("content", paramString);
            values.put("state", State.TODO.intValue);
            values.put("date", System.currentTimeMillis());
            //TODO: priority
            values.put("priority", paramPriority.priority);
            if (this.database.insert("note", null, values) != -1L) {
                bool = true;
            }

            return bool;
        }
        return bool;
    }
}
