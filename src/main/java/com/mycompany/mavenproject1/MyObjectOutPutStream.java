/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.mavenproject1;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/**
 * 
 * @author KevinChevez
 */
public class MyObjectOutPutStream extends ObjectOutputStream{

    public MyObjectOutPutStream(OutputStream out) throws IOException {
        super(out);
    }

    public MyObjectOutPutStream() throws IOException, SecurityException {
    }
    
    
    @Override
    protected void writeStreamHeader() throws IOException{
        //Sin nada que hacer :)
    }
}
