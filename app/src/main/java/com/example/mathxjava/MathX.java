package com.example.mathxjava;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class MathX extends AppCompatActivity {
    private Button Button_Dalee;

    public TextView textView_Zadanie;          //textView_Zadanie вывод задания
    public TextView  textView_NomerZadania;    // задание отображение счетчика
    public TextView textView_PrimerProverka; //вывод проверки задания
    public EditText  editTextNumberOtvet;           // сюда пишут ответ
    public TextView textView_Timers;

   // private TextView timerValue;
    private long startTime = 0L;
    private Handler customHandler = new Handler();
    long timeInMilliseconds = 0L;
    long timeSwapBuff = 0L;
    long updatedTime = 0L;


    private final static String FILE_NAME = "content.txt";

    /*
    Добавить время, вывод времени
    Добавить сохранение всего в результаты
    Поработать над стилем приложения
    сделать подсведку "проверки примера" фона красным или зеленым

    Подзадачи
    кнопка завершить после 20 далее выход назад на окно ввода имени или же начать заново с этим же именем
    кнопки назад, выход, настройка и результаты
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_x);

        Button_Dalee =(Button)findViewById(R.id.Button_Dalee);
        textView_Zadanie=(TextView)findViewById(R.id.textView_Zadanie);
        textView_NomerZadania=(TextView)findViewById(R.id.textView_NomerZadania);
        textView_PrimerProverka=(TextView)findViewById(R.id.textView_PrimerProverka);
        textView_Timers=(TextView)findViewById(R.id.textView_Timers);

        editTextNumberOtvet= (EditText)findViewById(R.id.editTextNumberOtvet);

        VivodZadachi();

        //Start Timer
        startTime = SystemClock.uptimeMillis();
        customHandler.postDelayed(updateTimerThread, 0);

        textView_NomerZadania.setText("Задание "+NumberZ+" из 20 ");

        Button_Dalee.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Dalee();
                    }
                }
        );
    }

    int numder1=0;
    int numder2 =0; //числа и знак
    String znak;
    int bolZnak;
    int Otvet=0;
    int NumberZ=1;
    int OtvetUser = 0;
    int pravOtvet;
    String name;
    public String saveString;
    int cal=0;
    int Timesmin,Timessec;

    String[] buffer ;

    //Timer
    private Runnable updateTimerThread = new Runnable() {
        public void run() {
            timeInMilliseconds = SystemClock.uptimeMillis() - startTime;
            updatedTime = timeSwapBuff + timeInMilliseconds;

            int secs = (int) (updatedTime / 1000);
            int mins = secs / 60;
            secs = secs % 60;
            int milliseconds = (int) (updatedTime % 1000);
            textView_Timers.setText("" + mins + ":" + String.format("%02d", secs));
            Timesmin = mins;
            Timessec = secs;
            customHandler.postDelayed(this, 0);
        }
    };

    public void Dalee(){
     //кнопка далее
     // при нажатии вывод резултата правильно или нет
     // следующая задача
     // где вывод при правильном ответе подсвечивать зеленым, неправильным красным

        //textView_Timers=
        //начало цикла
        if(NumberZ<20){
            Compare();//Запуск проверки и вывод
            VivodZadachi();

            editTextNumberOtvet.setText("");//обнуление строки ввода
            NumberZ++;
        }else{
            //Stop Timer
            timeSwapBuff += timeInMilliseconds;
            customHandler.removeCallbacks(updateTimerThread);
            if(cal==0) Compare();

            Button_Dalee.setText("Завершить");
            textView_Zadanie.setText("Всего решено: "+pravOtvet+" из 20 задач!");
            Save();

            //textView_PrimerProverka.setText("Проверка! "+saveString);
            cal++;
            if(cal>1) {
                //начало сохранения в файл
                FileOutputStream fos = null;
                try {
                    // EditText textBox = findViewById(R.id.editor);

                    fos = openFileOutput(FILE_NAME, MODE_PRIVATE|MODE_APPEND);
                    fos.write(saveString.getBytes());

                    Toast.makeText(this, "Файл сохранен", Toast.LENGTH_SHORT).show();
                }
                catch(IOException ex) {

                    Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
                finally{
                    try{
                        if(fos!=null)
                            fos.close();
                    }
                    catch(IOException ex){

                        Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                Intent intent = new Intent(this, MainActivity.class);
               startActivity(intent);//переход на главную страницу

                // pravOtvet вывод правильных ответов
                //смена имени кнопки далее на кнопку завершить
                //сообщение о выходе
            }
        }
        textView_NomerZadania.setText("Задание "+NumberZ+" из 20 ");
       // textView_PrimerProverka.setText("Проверка №"+ NumberZ+" Записанное число: "+OtvetUser);
    }

    //textView_Zadanie вывод задания



    public void VivodZadachi(){
            Random random = new Random();
            numder1 = random.nextInt(100);
            numder2 = random.nextInt(numder1);
            ViborZnaka();
            textView_Zadanie.setText(numder1 + znak + numder2);

    }


    public void ViborZnaka(){
        //int mass[] = (numder1 + numder2; numder1-numder2);
        Random random = new Random();
        bolZnak= random.nextInt(2);
        if (bolZnak==1) {
            znak = "+";
            ZnakPlus();
        } else{
            znak ="-";
            ZnakMinus();
        }
    }

    public void ZnakPlus(){
        Otvet = numder1+numder2;
    }
    public void ZnakMinus(){
        Otvet = numder1-numder2;
    }

    public void Compare(){
        if(editTextNumberOtvet.getText().toString().equals("")){
            textView_PrimerProverka.setText("Ничего не введено.Правильный ответ: " + Otvet);
            textView_PrimerProverka.setBackgroundColor(getResources().getColor(R.color.red));
        }else {
            OtvetUser= Integer.parseInt(editTextNumberOtvet.getText().toString());// запись из эдит в ответЮзер
            if (Otvet == OtvetUser) {
                pravOtvet++;
                textView_PrimerProverka.setText("Решение правильное. Молодец!");
                textView_PrimerProverka.setBackgroundColor(getResources().getColor(R.color.green));
            } else{
                textView_PrimerProverka.setText("Решение не правильное. Правильный ответ: " + Otvet);
            textView_PrimerProverka.setBackgroundColor(getResources().getColor(R.color.red));
            }
        }

    }

    Date currentDate = new Date();
    DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
    String dateText = dateFormat.format(currentDate);
    DateFormat timeFormat = new SimpleDateFormat("HH.mm.ss", Locale.getDefault());
    String timeText = timeFormat.format(currentDate);

    public void Save(){
       //Rezult.editTextTextMultiLine;
        Bundle arguments =getIntent().getExtras();
        name = arguments.get("savename").toString();
        saveString = "Имя:"+name+" "+pravOtvet+"/20"+" время "+Timesmin+":"+Timessec+" "+"("+dateText+"/"+timeText+")"+"\n";
        //textView_PrimerProverka.setText("Проверка! "+saveString);

        }

        //Intent intent = new Intent(this,Rezult.class);
        //intent.putExtra("save",saveString);
        //startActivity(intent);
    }

