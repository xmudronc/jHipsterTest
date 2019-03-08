import { element, by, promise, ElementFinder } from 'protractor';

export class UsrComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    title = element.all(by.css('jhi-usr div h2#page-heading span')).first();

    clickOnCreateButton(): promise.Promise<void> {
        return this.createButton.click();
    }

    getTitle(): any {
        return this.title.getText();
    }
}

export class UsrUpdatePage {
    pageTitle = element(by.id('jhi-usr-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    userIDInput = element(by.id('field_userID'));
    passwordInput = element(by.id('field_password'));

    getPageTitle() {
        return this.pageTitle.getText();
    }

    setUserIDInput(userID): promise.Promise<void> {
        return this.userIDInput.sendKeys(userID);
    }

    getUserIDInput() {
        return this.userIDInput.getAttribute('value');
    }

    setPasswordInput(password): promise.Promise<void> {
        return this.passwordInput.sendKeys(password);
    }

    getPasswordInput() {
        return this.passwordInput.getAttribute('value');
    }

    save(): promise.Promise<void> {
        return this.saveButton.click();
    }

    cancel(): promise.Promise<void> {
        return this.cancelButton.click();
    }

    getSaveButton(): ElementFinder {
        return this.saveButton;
    }
}
