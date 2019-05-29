/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { ChatMessageComponentsPage, ChatMessageDeleteDialog, ChatMessageUpdatePage } from './chat-message.page-object';

const expect = chai.expect;

describe('ChatMessage e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let chatMessageUpdatePage: ChatMessageUpdatePage;
  let chatMessageComponentsPage: ChatMessageComponentsPage;
  let chatMessageDeleteDialog: ChatMessageDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load ChatMessages', async () => {
    await navBarPage.goToEntity('chat-message');
    chatMessageComponentsPage = new ChatMessageComponentsPage();
    await browser.wait(ec.visibilityOf(chatMessageComponentsPage.title), 5000);
    expect(await chatMessageComponentsPage.getTitle()).to.eq('spingularchatsqlApp.chatMessage.home.title');
  });

  it('should load create ChatMessage page', async () => {
    await chatMessageComponentsPage.clickOnCreateButton();
    chatMessageUpdatePage = new ChatMessageUpdatePage();
    expect(await chatMessageUpdatePage.getPageTitle()).to.eq('spingularchatsqlApp.chatMessage.home.createOrEditLabel');
    await chatMessageUpdatePage.cancel();
  });

  it('should create and save ChatMessages', async () => {
    const nbButtonsBeforeCreate = await chatMessageComponentsPage.countDeleteButtons();

    await chatMessageComponentsPage.clickOnCreateButton();
    await promise.all([
      chatMessageUpdatePage.setMessageSentAtInput('messageSentAt'),
      chatMessageUpdatePage.setMessageInput('message'),
      chatMessageUpdatePage.chatRoomSelectLastOption(),
      chatMessageUpdatePage.chatUserSelectLastOption()
    ]);
    expect(await chatMessageUpdatePage.getMessageSentAtInput()).to.eq(
      'messageSentAt',
      'Expected MessageSentAt value to be equals to messageSentAt'
    );
    expect(await chatMessageUpdatePage.getMessageInput()).to.eq('message', 'Expected Message value to be equals to message');
    const selectedIsDelivered = chatMessageUpdatePage.getIsDeliveredInput();
    if (await selectedIsDelivered.isSelected()) {
      await chatMessageUpdatePage.getIsDeliveredInput().click();
      expect(await chatMessageUpdatePage.getIsDeliveredInput().isSelected(), 'Expected isDelivered not to be selected').to.be.false;
    } else {
      await chatMessageUpdatePage.getIsDeliveredInput().click();
      expect(await chatMessageUpdatePage.getIsDeliveredInput().isSelected(), 'Expected isDelivered to be selected').to.be.true;
    }
    const selectedIsOffensive = chatMessageUpdatePage.getIsOffensiveInput();
    if (await selectedIsOffensive.isSelected()) {
      await chatMessageUpdatePage.getIsOffensiveInput().click();
      expect(await chatMessageUpdatePage.getIsOffensiveInput().isSelected(), 'Expected isOffensive not to be selected').to.be.false;
    } else {
      await chatMessageUpdatePage.getIsOffensiveInput().click();
      expect(await chatMessageUpdatePage.getIsOffensiveInput().isSelected(), 'Expected isOffensive to be selected').to.be.true;
    }
    await chatMessageUpdatePage.save();
    expect(await chatMessageUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await chatMessageComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last ChatMessage', async () => {
    const nbButtonsBeforeDelete = await chatMessageComponentsPage.countDeleteButtons();
    await chatMessageComponentsPage.clickOnLastDeleteButton();

    chatMessageDeleteDialog = new ChatMessageDeleteDialog();
    expect(await chatMessageDeleteDialog.getDialogTitle()).to.eq('spingularchatsqlApp.chatMessage.delete.question');
    await chatMessageDeleteDialog.clickOnConfirmButton();

    expect(await chatMessageComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
