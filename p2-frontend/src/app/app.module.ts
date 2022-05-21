import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import {MatCardModule} from '@angular/material/card';


import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http'
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './component/app.component';
import { NavBarComponent } from './component/nav-bar/nav-bar.component';
import { UserViewComponent } from './pages/user-view/user-view.component';
import { FormsModule } from '@angular/forms';
import { UserLoginComponent } from './pages/user-login/user-login.component';
import { UserRegistrationComponent } from './pages/user-registration/user-registration.component';
import { ContentComponent } from './content/content.component';
import { AuthService } from './services/auth.service';
import { ProductViewsComponent } from './pages/product-views/product-views.component';
import { SearchBoxComponent } from './component/search-box/search-box.component';
import { BannerComponent } from './component/banner/banner.component';
import { HeaderComponent } from './component/header/header.component';
import { LandingComponent } from './pages/landing/landing.component';
import { CategoryComponent} from './component/category/category.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ProductComponent } from './component/product/product.component'

@NgModule({
  declarations: [
    AppComponent,
    NavBarComponent,
    UserViewComponent,
    UserLoginComponent,
    UserRegistrationComponent,
    ContentComponent,
    ProductViewsComponent,
    SearchBoxComponent,
    HeaderComponent,
    LandingComponent,
    BannerComponent,
    CategoryComponent,
    ProductComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatIconModule,
    MatCardModule,
  ],
  providers: [AuthService],
  bootstrap: [AppComponent]
})
export class AppModule { }
