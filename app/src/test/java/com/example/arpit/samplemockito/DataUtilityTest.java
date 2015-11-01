package com.example.arpit.samplemockito;


import android.content.Context;
import android.content.SharedPreferences;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.Whitebox;

public class DataUtilityTest {

    DataUtility dataUtility;
    Context mockedContext;

    @Before
    public void setUp() {
        mockedContext = Mockito.mock(Context.class);
        dataUtility = new DataUtility(mockedContext);
    }

    @After
    public void tearDown() {
        dataUtility = null;
    }

    @Test
    public void reverseString_validInput_returnInput() {
        StoredData mockedStoredData = Mockito.mock(StoredData.class);
        Mockito.when(mockedStoredData.revereTheInputString(Mockito.anyString())).thenReturn("xyz");
        Whitebox.setInternalState(dataUtility, "storedData", mockedStoredData);
        Assert.assertEquals("xyz", dataUtility.reverseInputString("anyString"));
    }

    @Test
    public void storeCurrentTime_shouldStoreTime(){
        SharedPreferences mockedSharedPreference = Mockito.mock(SharedPreferences.class);
        Mockito.when(mockedContext.getSharedPreferences(Mockito.anyString(), Mockito.anyInt())).thenReturn(mockedSharedPreference);
        SharedPreferences.Editor mockedEditor = Mockito.mock(SharedPreferences.Editor.class);
        Mockito.when(mockedSharedPreference.edit()).thenReturn(mockedEditor);
        dataUtility.storeCurrentTime();
        Mockito.verify(mockedEditor).apply();
    }


    @Test
    public void getStoredTime_returnStoredTime(){
        SharedPreferences mockedSharedPreference = Mockito.mock(SharedPreferences.class);
        Mockito.when(mockedContext.getSharedPreferences(Mockito.anyString(), Mockito.anyInt())).thenReturn(mockedSharedPreference);
        Mockito.when(mockedSharedPreference.contains(Mockito.anyString())).thenReturn(true);
        Mockito.when(mockedSharedPreference.getLong(Mockito.anyString(), Mockito.anyLong())).thenReturn(122l);
        Assert.assertEquals(122l, dataUtility.getStoredTime());
    }

    @Test
    public void getStoredTime_timeIsNotStored_returnZero(){
        SharedPreferences mockedSharedPreference = Mockito.mock(SharedPreferences.class);
        Mockito.when(mockedContext.getSharedPreferences(Mockito.anyString(), Mockito.anyInt())).thenReturn(mockedSharedPreference);
        Mockito.when(mockedSharedPreference.contains(Mockito.anyString())).thenReturn(false);
        Assert.assertEquals(0,dataUtility.getStoredTime());
    }
}
