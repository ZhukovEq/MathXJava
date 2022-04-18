package com.example.mathxjava;

import static android.content.Context.MODE_PRIVATE;

import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class ZadanieMath {
    ArrayList<String> ArrBuffer= new ArrayList();
    ArrayList<String> ArrConst= new ArrayList();
    ZadanieMath(){

    }

    void SaveArrayBuffered(String m){
     ArrBuffer.add(m);
    }
    void SaveArray(String n){
        ArrConst.add(n);
    }
}
