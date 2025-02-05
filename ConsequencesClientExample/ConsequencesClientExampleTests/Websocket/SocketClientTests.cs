﻿using ConsequencesClientExample.Websocket;

namespace ConsequencesClientExampleTests.Websocket
{
    internal class SocketClientTests
    {
        [Test]
        public void Receive_WhenMessageListManuallyFilled_ReturnsMessage()
        {
            // Arrange
            SocketClient socketClient = new SocketClient();
            socketClient._responseList.Add("{\"Message\":\"Test\"}");

            // Act
            var response = socketClient.Receive();

            // Assert
            Assert.That(response.Message, Is.EqualTo("Test"));
        }
    }
}
