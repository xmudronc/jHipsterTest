import { element, by, promise, ElementFinder } from 'protractor';

export class ProductComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    title = element.all(by.css('jhi-product div h2#page-heading span')).first();

    clickOnCreateButton(): promise.Promise<void> {
        return this.createButton.click();
    }

    getTitle(): any {
        return this.title.getText();
    }
}

export class ProductUpdatePage {
    pageTitle = element(by.id('jhi-product-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    productIDInput = element(by.id('field_productID'));
    priceInput = element(by.id('field_price'));
    categoryInput = element(by.id('field_category'));
    imageInput = element(by.id('file_image'));
    usrSelect = element(by.id('field_usr'));

    getPageTitle() {
        return this.pageTitle.getText();
    }

    setProductIDInput(productID): promise.Promise<void> {
        return this.productIDInput.sendKeys(productID);
    }

    getProductIDInput() {
        return this.productIDInput.getAttribute('value');
    }

    setPriceInput(price): promise.Promise<void> {
        return this.priceInput.sendKeys(price);
    }

    getPriceInput() {
        return this.priceInput.getAttribute('value');
    }

    setCategoryInput(category): promise.Promise<void> {
        return this.categoryInput.sendKeys(category);
    }

    getCategoryInput() {
        return this.categoryInput.getAttribute('value');
    }

    setImageInput(image): promise.Promise<void> {
        return this.imageInput.sendKeys(image);
    }

    getImageInput() {
        return this.imageInput.getAttribute('value');
    }

    usrSelectLastOption(): promise.Promise<void> {
        return this.usrSelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    usrSelectOption(option): promise.Promise<void> {
        return this.usrSelect.sendKeys(option);
    }

    getUsrSelect(): ElementFinder {
        return this.usrSelect;
    }

    getUsrSelectedOption() {
        return this.usrSelect.element(by.css('option:checked')).getText();
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
