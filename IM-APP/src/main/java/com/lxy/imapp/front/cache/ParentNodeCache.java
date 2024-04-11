package com.lxy.imapp.front.cache;

import com.lxy.imapp.front.data.GroupsData;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;

import java.util.Iterator;

public class ParentNodeCache {
    public static ListView<Pane> firendListView;

    public static Pane friendUserListPane;

    public static ListView<Pane> groupListView;
    public static Pane friendGroupPane;

    public static void removeFriend(String userId){
        Platform.runLater(()->{
            if(firendListView != null){
                ObservableList<Pane> items = firendListView.getItems();
                if(items != null){
                    Iterator<Pane> iterator = items.iterator();
                    while(iterator.hasNext()){
                        Pane next = iterator.next();
                        String friendId = (String) next.getUserData();
                        if(friendId.equals(userId)){
                            iterator.remove();
                            break;
                        }

                    }

                }
                items = firendListView.getItems();
                firendListView.setPrefHeight(80 * items.size());

                if(friendUserListPane != null){
                    friendUserListPane.setPrefHeight(80 * items.size());
                }

            }
        });

    }

    public static boolean isFriend(String userId){

        if(firendListView != null){
            ObservableList<Pane> items = firendListView.getItems();
            if(items != null){
                Iterator<Pane> iterator = items.iterator();
                while(iterator.hasNext()){
                    Pane next = iterator.next();
                    String friendId = (String) next.getUserData();
                    if(friendId.equals(userId)){

                        return true;
                    }

                }

            }

        }
        return false;
    }

    public static void removeGroup(String groupId){
        Platform.runLater(()->{
            if(groupListView != null){
                ObservableList<Pane> items = groupListView.getItems();
                if(items != null){
                    Iterator<Pane> iterator = items.iterator();
                    while(iterator.hasNext()){
                        Pane next = iterator.next();
                        GroupsData joinGroupId = (GroupsData) next.getUserData();
                        if(joinGroupId.getGroupId().equals(groupId)){
                            iterator.remove();
                            break;
                        }

                    }

                }
                System.out.println("items.size" + items.size());
                items = groupListView.getItems();
                groupListView.setPrefHeight(80 * items.size());
                if(friendGroupPane!=null)
                    friendGroupPane.setPrefHeight(80 * items.size());
            }
        });

    }

    public static boolean isInGroup(String groupId){
        if(groupListView != null){
            ObservableList<Pane> items = groupListView.getItems();
            if(items != null){
                Iterator<Pane> iterator = items.iterator();
                while(iterator.hasNext()){
                    Pane next = iterator.next();
                    GroupsData joinGroupId = (GroupsData) next.getUserData();
                    if(joinGroupId.getGroupId().equals(groupId)){
                        return true;
                    }

                }

            }

        }

        return false;
    }
}
