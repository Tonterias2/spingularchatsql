import { Component, OnInit, OnDestroy } from '@angular/core';
import { NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { ChatService } from '../shared';

import { LoginModalService, AccountService, Account } from 'app/core';

import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { IChatRoom } from 'app/shared/model/chat-room.model';

import { ITEMS_PER_PAGE } from 'app/shared';
import { ChatRoomService } from '../entities/chat-room/chat-room.service';

import { IChatMessage } from 'app/shared/model/chat-message.model';
import { ChatMessageService } from '../entities/chat-message/chat-message.service';

@Component({
  selector: 'jhi-home',
  templateUrl: './home.component.html',
  styleUrls: ['home.scss']
})
export class HomeComponent implements OnInit {
  account: Account;
  modalRef: NgbModalRef;
  messages: Array<Object> = [];
  message = '';

  chatMessages: IChatMessage[];

  currentAccount: any;
  chatRooms: IChatRoom[];
  error: any;
  success: any;
  eventSubscriber: Subscription;
  routeData: any;
  links: any;
  totalItems: any;
  itemsPerPage: any;
  page: any;
  predicate: any;
  previousPage: any;
  reverse: any;

  constructor(
    private accountService: AccountService,
    private loginModalService: LoginModalService,
    private eventManager: JhiEventManager,
    private chatService: ChatService,
    protected chatRoomService: ChatRoomService,
    protected chatMessageService: ChatMessageService,
    protected parseLinks: JhiParseLinks,
    protected jhiAlertService: JhiAlertService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router
  ) {}

  ngOnInit() {
    this.chatService.connect();

    this.chatService.receive().subscribe(message => {
      this.messages.push(message);
    });

    this.accountService.identity().then(account => {
      this.account = account;
    });

    this.loadAll();
    this.accountService.identity().then(account => {
      this.currentAccount = account;
    });
    this.registerChangeInChatRooms();

    this.registerAuthenticationSuccess();
    this.registerLogoutSuccess();
  }

  registerAuthenticationSuccess() {
    this.eventManager.subscribe('authenticationSuccess', message => {
      this.accountService.identity().then(account => {
        this.account = account;
        this.chatService.disconnect();
        this.chatService.connect();
      });
    });
  }
  registerLogoutSuccess() {
    this.eventManager.subscribe('logoutSuccess', message => {
      this.chatService.disconnect();
      this.chatService.connect();
    });
  }

  isAuthenticated() {
    return this.accountService.isAuthenticated();
  }

  login() {
    this.modalRef = this.loginModalService.open();
  }

  sendMessage(message) {
    if (message.length === 0) {
      return;
    }
    this.chatService.sendMessage(message);
    this.message = '';
  }

  loadAll() {
    this.chatRoomService
      .query({
        //        page: this.page - 1,
        //        size: this.itemsPerPage,
        //        sort: this.sort()
      })
      .subscribe(
        (res: HttpResponse<IChatRoom[]>) => this.paginateChatRooms(res.body, res.headers),
        (res: HttpErrorResponse) => this.onError(res.message)
      );
  }

  fetchChatRoom(chatRoomId) {
    console.log('CONSOLOG: M:fetchChatRoom & O: chatRoomId : ', chatRoomId);
    if (chatRoomId !== undefined) {
      const query = {};
      query['chatRoomId.equals'] = chatRoomId;
      query['queryParams'] = 1;
      console.log('CONSOLOG: M:fetchChatRoom & O: query : ', query);
      this.chatMessageService.query(query).subscribe(
        (res: HttpResponse<IChatMessage[]>) => {
          this.messages = res.body;
          console.log('CONSOLOG: M:fetchChatRoom & O: this.messages : ', this.messages);
        },
        (res: HttpErrorResponse) => this.onError(res.message)
      );
    }
  }

  registerChangeInChatRooms() {
    this.eventSubscriber = this.eventManager.subscribe('chatRoomListModification', response => this.loadAll());
  }

  sort() {
    const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateChatRooms(data: IChatRoom[], headers: HttpHeaders) {
    this.links = this.parseLinks.parse(headers.get('link'));
    this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
    this.chatRooms = data;
    console.log('CONSOLOG: M:paginateChatRooms & O: this.chatRooms : ', this.chatRooms);
  }

  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }
}
