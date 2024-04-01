package com.lxy.imapp.biz.file;

import com.lxy.protocolpackage.dto.ChatRecordDto;

import java.util.*;

public class ChatRecordMap{

   private static Map<String, List<ChatRecordDto>> chatRecordDtoMap = new HashMap<>();

   public static boolean updated = false;
   public static void appendChatRecordDto(ChatRecordDto chatRecordDto){
       updated = true;
       List<ChatRecordDto> chatRecordDtos = chatRecordDtoMap.get(chatRecordDto.getTalkId());
       if(chatRecordDtos == null) {
           chatRecordDtos = new ArrayList<>();
           chatRecordDtoMap.put(chatRecordDto.getTalkId(), chatRecordDtos);
       }
       chatRecordDtos.add(chatRecordDto);

   }

   public static void setChatRecordDtoMap(Map<String, List<ChatRecordDto>> chatRecordDtoMapOut){
       chatRecordDtoMap = chatRecordDtoMapOut;
   }

   public static List<ChatRecordDto> getChatRecordDtoList(){
       List<ChatRecordDto> chatRecordDtoList = new ArrayList<>();
       Set<Map.Entry<String, List<ChatRecordDto>>> entries = chatRecordDtoMap.entrySet();
       for(Map.Entry<String, List<ChatRecordDto>> entry : entries){
           List<ChatRecordDto> value = entry.getValue();
           chatRecordDtoList.addAll(value);
       }

       return chatRecordDtoList;
   }

   public static List<ChatRecordDto> getChatRecordDtoListById(String talkId){
       return chatRecordDtoMap.get(talkId);
   }
}
