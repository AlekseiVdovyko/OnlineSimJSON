package ru.edu.service;

public class OnlineSimInfo {

    private String codeCountry;
    private String nameCountry;
    private String numbers;

    public OnlineSimInfo() {
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private OnlineSimInfo info = new OnlineSimInfo();

        public Builder setCodeCountry(String codeCountry) {
            info.codeCountry = codeCountry;
            return this;
        }

        public Builder setNameCountry(String nameCountry) {
            info.nameCountry = nameCountry;
            return this;
        }

        public Builder setNumbers(String numbers) {
            info.numbers = numbers;
            return this;
        }

        public OnlineSimInfo build() {
            return info;
        }
    }

    public String getCodeCountry() {
        return codeCountry;
    }

    public String getNameCountry() {
        return nameCountry;
    }

    public String getNumbers() {
        return numbers;
    }

    @Override
    public String toString() {
        return "OnlineSimInfo{" +
                "codeCountry=" + codeCountry +
                ", nameCountry='" + nameCountry + '\'' +
                ", numbers='" + numbers + '\'' +
                '}';
    }
}
