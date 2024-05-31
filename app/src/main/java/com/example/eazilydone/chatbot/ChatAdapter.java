package com.example.eazilydone.chatbot;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eazilydone.R;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Message> messageList;
    public ChatAdapter(List<Message> messageList) {
        this.messageList = messageList;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == Message.TYPE_BOT) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bot_msg, parent, false);
            return new BotMessageViewHolder(view);
        }
        else{
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_msg, parent, false);
            return new UserMessageViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Message message = messageList.get(position);
        if (holder.getItemViewType() == Message.TYPE_BOT) {
            ((BotMessageViewHolder) holder).bind(message);
        } else {
            ((UserMessageViewHolder) holder).bind(message);
        }
    }
    @Override
    public int getItemViewType(int position) {
        return messageList.get(position).getType();
    }
    public void addMessage(Message message) {
        messageList.add(message);
        notifyItemInserted(messageList.size() - 1);
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }
    static class UserMessageViewHolder extends RecyclerView.ViewHolder{
        TextView textViewMessage;
        UserMessageViewHolder(@NonNull View itemView){
            super(itemView);
            textViewMessage = itemView.findViewById(R.id.userMsg);
        }
        void bind(Message message){
            textViewMessage.setText(message.getMessage());
        }
    }
    static class BotMessageViewHolder extends RecyclerView.ViewHolder{
        TextView textViewMessage;
        BotMessageViewHolder(@NonNull View itemView){
            super(itemView);
            textViewMessage = itemView.findViewById(R.id.botMsg);
        }
        void bind(Message message){
            textViewMessage.setText(message.getMessage());

        }
    }
}

