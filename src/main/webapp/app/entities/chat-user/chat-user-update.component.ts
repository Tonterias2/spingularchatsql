import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';
import { IChatUser, ChatUser } from 'app/shared/model/chat-user.model';
import { ChatUserService } from './chat-user.service';
import { IUser, UserService } from 'app/core';
import { IChatRoom } from 'app/shared/model/chat-room.model';
import { ChatRoomService } from 'app/entities/chat-room';

@Component({
  selector: 'jhi-chat-user-update',
  templateUrl: './chat-user-update.component.html'
})
export class ChatUserUpdateComponent implements OnInit {
  chatUser: IChatUser;
  isSaving: boolean;

  users: IUser[];

  chatrooms: IChatRoom[];

  editForm = this.fb.group({
    id: [],
    creationDate: [null, [Validators.required]],
    bannedUser: [],
    userId: [],
    chatRooms: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected chatUserService: ChatUserService,
    protected userService: UserService,
    protected chatRoomService: ChatRoomService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ chatUser }) => {
      this.updateForm(chatUser);
      this.chatUser = chatUser;
    });
    this.userService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IUser[]>) => mayBeOk.ok),
        map((response: HttpResponse<IUser[]>) => response.body)
      )
      .subscribe((res: IUser[]) => (this.users = res), (res: HttpErrorResponse) => this.onError(res.message));
    this.chatRoomService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IChatRoom[]>) => mayBeOk.ok),
        map((response: HttpResponse<IChatRoom[]>) => response.body)
      )
      .subscribe((res: IChatRoom[]) => (this.chatrooms = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(chatUser: IChatUser) {
    this.editForm.patchValue({
      id: chatUser.id,
      creationDate: chatUser.creationDate != null ? chatUser.creationDate.format(DATE_TIME_FORMAT) : null,
      bannedUser: chatUser.bannedUser,
      userId: chatUser.userId,
      chatRooms: chatUser.chatRooms
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const chatUser = this.createFromForm();
    if (chatUser.id !== undefined) {
      this.subscribeToSaveResponse(this.chatUserService.update(chatUser));
    } else {
      this.subscribeToSaveResponse(this.chatUserService.create(chatUser));
    }
  }

  private createFromForm(): IChatUser {
    const entity = {
      ...new ChatUser(),
      id: this.editForm.get(['id']).value,
      creationDate:
        this.editForm.get(['creationDate']).value != null ? moment(this.editForm.get(['creationDate']).value, DATE_TIME_FORMAT) : undefined,
      bannedUser: this.editForm.get(['bannedUser']).value,
      userId: this.editForm.get(['userId']).value,
      chatRooms: this.editForm.get(['chatRooms']).value
    };
    return entity;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IChatUser>>) {
    result.subscribe((res: HttpResponse<IChatUser>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  trackUserById(index: number, item: IUser) {
    return item.id;
  }

  trackChatRoomById(index: number, item: IChatRoom) {
    return item.id;
  }

  getSelected(selectedVals: Array<any>, option: any) {
    if (selectedVals) {
      for (let i = 0; i < selectedVals.length; i++) {
        if (option.id === selectedVals[i].id) {
          return selectedVals[i];
        }
      }
    }
    return option;
  }
}
