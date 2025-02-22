﻿using ConsequencesClientExample.Messaging;
using System.Collections.Concurrent;
using System.Text.Json;
using Websocket.Client;

namespace ConsequencesClientExample.Websocket
{
    public class SocketClient : ISocketClient
    {
        private ManualResetEvent _exitEvent;
        private WebsocketClient _client;
        internal BlockingCollection<string> _responseList;

        public SocketClient()
        {
            _responseList = new BlockingCollection<string>();
            _exitEvent = new ManualResetEvent(false);
        }

        public async void Connect(string address)
        {
            _client = new WebsocketClient(new Uri(address));
            _client.MessageReceived.Subscribe(response => _responseList.Add(response.Text));
            await _client.Start();

            _exitEvent.WaitOne();
        }

        public void Send(string start = "", string name = "", string room = "", string answer = "")
        {
            OutboundMessage message = new OutboundMessage { Start = start, Name = name, Room = room, Answer = answer };
            var serialisedMessage = JsonSerializer.Serialize(message);
            _client.Send(serialisedMessage);
        }

        public InboundResponse Receive()
        {
            var response = _responseList.Take();
            var parsedResponse = InboundResponseParser.Parse(response);
            return parsedResponse;
        }
    }
}
