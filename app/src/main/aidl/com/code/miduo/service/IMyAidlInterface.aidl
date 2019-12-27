// IMyAidlInterface.aidl
package com.code.miduo.service;
import com.code.miduo.service.Book;
// Declare any non-default types here with import statements

interface IMyAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
   void connect();

   void addBook(in Book book);

   List<Book> getBooks();
}
