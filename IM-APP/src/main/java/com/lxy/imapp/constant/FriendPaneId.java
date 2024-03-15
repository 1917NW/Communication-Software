package com.lxy.imapp.constant;

public enum FriendPaneId {
    NEW_FRIEND_PANE_ID("friend-new-friend-pane"),
    SUBSCRIBE_PANE_ID("subscribe_pane_id");
    private String id;

    FriendPaneId(String id){
        this.id = id;
    }

    public String getId(){
        return id;
    }
}
