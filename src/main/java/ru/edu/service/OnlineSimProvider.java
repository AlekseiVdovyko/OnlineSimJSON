package ru.edu.service;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import org.springframework.web.client.RestTemplate;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class OnlineSimProvider {

    public OnlineSimProvider() {
    }

    public OnlineSimInfo getInfo() {

        String codeCountry = "";
        String nameCountry = "";
        String numbers = "";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("result.txt"))) {
            String request = "https://onlinesim.ru/api/getFreeCountryList";
            RestTemplate restTemplate = new RestTemplate();
            String response = restTemplate.getForObject(request, String.class);
            JSONArray resp = JsonPath.parse(response).read("$.countries");

            for (int i = 0; i < resp.size(); i++) {
                Object obj = resp.get(i);

                String[] tmp = obj.toString().split(",");
                String tmp1 = tmp[0];
                String[] tmp2 = tmp1.toString().split("=");
                codeCountry = tmp2[1];

                String tmp3 = tmp[1];
                String[] tmp4 = tmp3.toString().split("=");
                String tmp5 = tmp4[1];
                String[] tmp6 = tmp5.toString().split("}");
                nameCountry = tmp6[0];


                String requestPhone = "https://onlinesim.ru/api/getFreePhoneList?country=" + codeCountry;
                RestTemplate restTemplatePhone = new RestTemplate();
                String responsePhone = restTemplatePhone.getForObject(requestPhone, String.class);
                JSONArray respPhone = JsonPath.parse(responsePhone).read("$.numbers");

                for (int k = 0; k < respPhone.size(); k++) {
                    String[] obj1 = respPhone.get(k).toString().split("full_number=");
                    String tmp8 = obj1[1];
                    String[] tmp7 = tmp8.toString().split(",");
                    numbers = tmp7[0];
                    writer.write("Код страны: " + codeCountry + "; ");
                    writer.write("Название страны: " + nameCountry + "; ");
                    writer.write("Номер телефона: " + numbers);
                    writer.newLine();

                    System.out.print("Code= " + codeCountry);
                    System.out.print("; NameCountry= " + nameCountry);
                    System.out.println("; FreePhone= " + numbers);

                }
            }
        } catch (Exception ex) {
            System.out.println("Something wrong");
        }

        System.out.println();
        System.out.println("The result was written to a file name \"result.txt\"");

        OnlineSimInfo codePhoneInfo = OnlineSimInfo.builder().
                setCodeCountry(codeCountry).
                setNameCountry(nameCountry).
                setNumbers(numbers).
                build();

        return codePhoneInfo;
    }
}
