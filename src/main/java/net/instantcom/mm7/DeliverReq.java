/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2007-2014 InstantCom Ltd. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License.  You can
 * obtain a copy of the License at
 * https://raw.github.com/vnesek/instantcom-mm7/master/LICENSE.txt
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at appropriate location.
 */

package net.instantcom.mm7;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jdom2.Element;

public class DeliverReq extends MM7Request implements HasContent {

	public String getApplicId() {
		return applicId;
	}

	public String getAuxApplicInfo() {
		return auxApplicInfo;
	}

	public Content getContent() {
		return content;
	}

	public String getLinkedId() {
		return linkedId;
	}

	public Priority getPriority() {
		return priority;
	}

	public List<Address> getRecipients() {
		return recipients;
	}

	public String getRecipientSPI() {
		return recipientSPI;
	}

	public String getReplyApplicId() {
		return replyApplicId;
	}

	public String getReplyChargingId() {
		return replyChargingId;
	}

	public Address getSender() {
		return sender;
	}

	public String getSenderSPI() {
		return senderSPI;
	}

	public String getSubject() {
		return Subject;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	@Override
	public void load(Element element) {
		super.load(element);

		Element body = element.getChild("Body", MM7Message.ENVELOPE);
		Element req = body.getChild("DeliverReq", namespace);

		Element sender = req.getChild("Sender", namespace);
		if (sender != null) {
			Address a = new Address();
			a.load((Element) sender.getChildren().get(0));
			setSender(a);
		} else {
			setSender(null);
		}

		setLinkedId(req.getChildTextTrim("LinkedID", namespace));
		setSenderSPI(req.getChildTextTrim("SenderSPI", namespace));
		setRecipientSPI(req.getChildTextTrim("RecipientSPI", namespace));
		setReplyChargingId(req.getChildTextTrim("ReplyChargingID", namespace));
		setSubject(req.getChildTextTrim("Subject", namespace));
		setApplicId(req.getChildTextTrim("ApplicID", namespace));
		setReplyApplicId(req.getChildTextTrim("ReplyApplicID", namespace));
		setAuxApplicInfo(req.getChildTextTrim("AuxApplicInfo", namespace));
		setPriority(Priority.valueOf(req.getChildTextTrim("Priority", namespace).toUpperCase()));
		setTimeStamp(new RelativeDate(req.getChildTextTrim("TimeStamp", namespace)).toDate());

	}

	public void setApplicId(String applicId) {
		this.applicId = applicId;
	}

	public void setAuxApplicInfo(String auxApplicInfo) {
		this.auxApplicInfo = auxApplicInfo;
	}

	public void setContent(Content content) {
		this.content = content;
	}

	public void setLinkedId(String linkedId) {
		this.linkedId = linkedId;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public void setRecipients(List<Address> recipients) {
		this.recipients = recipients;
	}

	public void setRecipientSPI(String recipientSPI) {
		this.recipientSPI = recipientSPI;
	}

	public void setReplyApplicId(String replyApplicId) {
		this.replyApplicId = replyApplicId;
	}

	public void setReplyChargingId(String replyChargingId) {
		this.replyChargingId = replyChargingId;
	}

	public void setSender(Address sender) {
		this.sender = sender;
	}

	public void setSenderSPI(String senderSPI) {
		this.senderSPI = senderSPI;
	}

	public void setSubject(String subject) {
		Subject = subject;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	@Override
	public DeliverRsp reply() {
		DeliverRsp response = new DeliverRsp();
		response.setMm7Version(getMm7Version());
		response.setNamespace(getNamespace());
		response.setTransactionId(getTransactionId());
		response.setStatusCode(MM7Response.SC_SUCCESS);
		return response;
	}

	private Address sender;
	private String linkedId;
	private List<Address> recipients = new ArrayList<Address>();
	// private PreviouslySentBy previouslySentBy;
	private String senderSPI;
	private String recipientSPI;
	private Date timeStamp;
	private String replyChargingId;
	private Priority priority;
	private String Subject;
	private String applicId;
	private String replyApplicId;
	private String auxApplicInfo;
	private Content content;
}