package com.proba;

import com.proba.dimtest.utils.DimTestUtil;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Main {
    private static  class SearchResultDto extends Object {
        private String accountId;
        private String accountName;
        private String typeName;
        private Type searchType;

        public SearchResultDto() {
        }

        public String getAccountId() {
            return accountId;
        }

        public void setAccountId(String accountId) {
            this.accountId = accountId;
        }

        public String getAccountName() {
            return accountName;
        }

        public void setAccountName(String accountName) {
            this.accountName = accountName;
        }

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }

        public Type getSearchType() {
            return searchType;
        }

        public void setSearchType(Type searchType) {
            this.searchType = searchType;
        }

/*        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof SearchResultDto)) return false;
            SearchResultDto that = (SearchResultDto) o;
            return Objects.equals(accountId, that.accountId) &&
                    Objects.equals(accountName, that.accountName) &&
                    Objects.equals(typeName, that.typeName) &&
                    searchType == that.searchType;
        }

        @Override
        public int hashCode() {
            return Objects.hash(accountId, accountName, typeName, searchType);
        }*/
    }
    static String method1(){
        SearchResultDto s = new SearchResultDto();
        s.setAccountId("453253");
        return String.valueOf(s);
    }

    static String method2(){
        SearchResultDto s = null;
        return String.valueOf(s);
    }

    public static void main(String[] args) {
        DimTestUtil.testClass(SampleTestClass.class);
        DimTestUtil.testClass(TestSampleObjectUtil.class);

        String s = "ssss:qqqq";
        System.out.println(method1());
        System.out.println(method2());

        System.out.println(s.split(":"));
        Set<SearchResultDto> newSet = new HashSet<>();
        SearchResultDto sr1 = new SearchResultDto();
        sr1.setAccountId("ID1");
        SearchResultDto sr2 = new SearchResultDto();
        sr2.setAccountId("ID2");
        SearchResultDto sr3 = new SearchResultDto();
        sr3.setAccountId("ID2");
        newSet.add(sr1);
        newSet.add(sr2);
        newSet.add(sr3);
        System.out.println("Size: " + newSet.size());
        System.out.println(newSet);
    }
}
