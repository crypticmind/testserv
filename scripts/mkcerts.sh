#!/bin/bash
cd "$(dirname "$0")/.."

SERVER_STORE=service/src/main/resources/keystore.jks
CLIENT_STORE=client/src/main/resources/keystore.jks

echo "1. Generating the Server Keystore."
rm -f ${SERVER_STORE}
keytool -genkeypair -alias testserv-service -keyalg RSA -dname "CN=localhost,OU=test,O=test,L=test,S=test,C=test" -keypass secret -keystore ${SERVER_STORE} -storepass secret

echo "2. Generating the Client Keystore."
rm -f ${CLIENT_STORE}
keytool -genkeypair -alias testserv-client -keyalg RSA -dname "CN=testserv-client,OU=test,O=test,L=test,S=test,C=test" -keypass secret -keystore ${CLIENT_STORE} -storepass secret

echo "3. Import the supported client's public certificates intro the server truststore."

echo "  - Export the client public certificate."
keytool -exportcert -alias testserv-client -file testserv-client.cert -keystore ${CLIENT_STORE} -storepass secret

echo "  - Import it in the server truststore."
keytool -importcert -noprompt -keystore ${SERVER_STORE} -alias testserv-client -file testserv-client.cert -storepass secret

rm -f testserv-client.cert

echo "4. Import the server's public certificate into the client truststores."

echo "  - Export the server public certificate."
keytool -exportcert -alias testserv-service -file testserv-service.cert -keystore ${SERVER_STORE} -storepass secret

echo "  - Import it in the client truststore."
keytool -importcert -noprompt -keystore ${CLIENT_STORE} -alias testserv-service -file testserv-service.cert -storepass secret

rm -f testserv-service.cert

echo "Done."
