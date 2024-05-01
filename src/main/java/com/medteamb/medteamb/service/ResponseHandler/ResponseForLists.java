package com.medteamb.medteamb.service.ResponseHandler;


import com.medteamb.medteamb.service.ResponseHandler.BaseResponse;
import org.springframework.http.HttpStatus;


import java.util.ArrayList;
import java.util.List;

public class ResponseForLists<T> extends BaseResponse {

   List<T> body = new ArrayList<>();

   private int numOfPages;
   private int numOfElements;
   private int currentPage;
   private Long totalElements;


   public ResponseForLists(String message, HttpStatus status){
       super(status, message);
   }

    public ResponseForLists(List<T> body) {
        this.body = body;
    }

    public int getNumOfPages() {
        return numOfPages;
    }

    public void setNumOfPages(int numOfPages) {
        this.numOfPages = numOfPages;
    }

    public int getNumOfElements() {
        return numOfElements;
    }

    public void setNumOfElements(int numOfElements) {
        this.numOfElements = numOfElements;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
    }


    public List<T> getBody() {
        return body;
    }
    public void setBody(List<T> body) {
        this.body = body;
    }


}
