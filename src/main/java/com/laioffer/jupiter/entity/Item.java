package com.laioffer.jupiter.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(builder = Item.Builder.class)

public class Item {
    @JsonProperty("id")
    private final String id;

    @JsonProperty("title")
    private final String title;

    @JsonProperty("thumbnail_url")
    private final String thumbnailUrl;

    @JsonProperty("broadcaster_name")
    @JsonAlias({ "user_name" })
    private String broadcasterName;

    //not final because in search stream provided by twitch, there's no url
    // if info provided by 3rd party matches our requirement, just use final to lock in the value
    @JsonProperty("url")
    private String url;

    @JsonProperty("game_id")
    private String gameId;

    @JsonProperty("item_type")
    private ItemType type;

    // builder is meant for producing a new Item.
    // Inner builder class is more suitable as it's tailored for Item class only
    private Item(Builder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.url = builder.url;
        this.thumbnailUrl = builder.thumbnailUrl;
        this.broadcasterName = builder.broadcasterName;
        this.gameId = builder.gameId;
        this.type = builder.type;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public Item setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public String getBroadcasterName() {
        return broadcasterName;
    }

    public String getGameId() {
        return gameId;
    }

    public Item setGameId(String gameId) {
        this.gameId = gameId;
        return this;
    }

    public ItemType getType() {
        return type;
    }

    public Item setType(ItemType type) {
        this.type = type;
        return this;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    // we need static here as without static, we need Object Item before we can have Builder
    // deadlock - unable to generate item since we need Builder
    public static class Builder {
        @JsonProperty("id")
        private String id;

        @JsonProperty("title")
        private String title;

        @JsonProperty("url")
        private String url;

        @JsonProperty("thumbnail_url")
        private String thumbnailUrl;

        @JsonProperty("broadcaster_name")
        @JsonAlias({ "user_name" })
        private String broadcasterName;

        @JsonProperty("game_id")
        private String gameId;

        @JsonProperty("item_type")
        private ItemType type;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder thumbnailUrl(String thumbnailUrl) {
            this.thumbnailUrl = thumbnailUrl;
            return this;
        }

        public Builder broadcasterName(String broadcasterName) {
            this.broadcasterName = broadcasterName;
            return this;
        }

        public Builder gameId(String gameId) {
            this.gameId = gameId;
            return this;
        }

        public Builder type(ItemType type) {
            this.type = type;
            return this;
        }

        public Item build() {
            return new Item(this);
        }
    }

}
