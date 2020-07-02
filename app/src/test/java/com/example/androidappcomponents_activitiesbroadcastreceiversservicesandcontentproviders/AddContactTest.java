package com.example.androidappcomponents_activitiesbroadcastreceiversservicesandcontentproviders;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class AddContactTest
{
    private AddContactFragment addContactFragment = new AddContactFragment();
    @Test
    public void aValidEmailAddress() throws Exception
    {
        assertTrue(addContactFragment.isValidEmailAddress("abc@xyz.com"));
    }
}