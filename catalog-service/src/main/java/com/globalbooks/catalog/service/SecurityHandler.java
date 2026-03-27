package com.globalbooks.catalog.service;

import java.util.Iterator;
import java.util.Set;
import javax.xml.namespace.QName;
import javax.xml.soap.*;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

public class SecurityHandler implements SOAPHandler<SOAPMessageContext> {
    @Override
    public boolean handleMessage(SOAPMessageContext context) {
        Boolean isOutbound = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        if (!isOutbound) {
            try {
                SOAPMessage soapMsg = context.getMessage();
                SOAPEnvelope soapEnv = soapMsg.getSOAPPart().getEnvelope();
                SOAPHeader soapHeader = soapEnv.getHeader();

                if (soapHeader == null) {
                    generateSOAPErrMessage(soapMsg, "No SOAP header found!");
                    return false;
                }

                Iterator headerElements = soapHeader.extractAllHeaderElements();
                boolean authenticated = false;

                while (headerElements.hasNext()) {
                    Node headerElement = (Node) headerElements.next();
                    if (headerElement.getLocalName().equals("Security")) {
                        String textContent = headerElement.getTextContent();
                        if (textContent.contains("admin") && textContent.contains("secret123")) {
                            authenticated = true;
                            break;
                        }
                    }
                }

                if (!authenticated) {
                    generateSOAPErrMessage(soapMsg, "Invalid Username or Password!");
                    return false;
                }
            } catch (Exception e) {
                System.out.println("Error reading SOAP Header: " + e.getMessage());
                return false;
            }
        }
        return true;
    }

    private void generateSOAPErrMessage(SOAPMessage msg, String reason) {
        try {
            SOAPBody soapBody = msg.getSOAPPart().getEnvelope().getBody();
            SOAPFault soapFault = soapBody.addFault();
            soapFault.setFaultString(reason);
            throw new RuntimeException(reason);
        } catch (SOAPException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean handleFault(SOAPMessageContext context) { return true; }
    @Override
    public void close(MessageContext context) {}
    @Override
    public Set<QName> getHeaders() { return null; }
}