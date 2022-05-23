import { FooterComponent } from './component/footer/footer.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import {MatCardModule} from '@angular/material/card';
import { SwiperModule } from 'swiper/angular';
import {MatGridListModule} from '@angular/material/grid-list';


import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http'
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavBarComponent } from './component/nav-bar/nav-bar.component';
import { HeaderComponent } from './component/header/header.component';
import { UserViewComponent } from './pages/user-view/user-view.component';
import { FormsModule } from '@angular/forms';
import { UserLoginComponent } from './pages/user-login/user-login.component';
import { UserRegistrationComponent } from './pages/user-registration/user-registration.component';
import { ContentComponent } from './content/content.component';
import { AuthService } from './services/auth.service';
import { ProductViewsComponent } from './pages/product-views/product-views.component';
import { SearchBoxComponent } from './component/search-box/search-box.component';
import { CartComponent } from './pages/cart/cart.component';
import { CheckoutComponent } from './pages/checkout/checkout.component';

import { BannerComponent } from './component/banner/banner.component';
import { LandingComponent } from './pages/landing/landing.component';
import { CategoryComponent} from './component/category/category.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ProductComponent } from './component/product/product.component';
import { FeaturedProductsComponent } from './component/featured-products/featured-products.component';
import { ProductDetailsComponent } from './pages/product-details/product-details.component';
import { LandingSection1Component } from './component/landing-section1/landing-section1.component';
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
    CartComponent,
    CheckoutComponent,
    HeaderComponent,
    LandingComponent,
    BannerComponent,
    CategoryComponent,
    ProductComponent,
    FeaturedProductsComponent,
    ProductDetailsComponent,
    FooterComponent,
    LandingSection1Component,


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
    MatGridListModule,
    SwiperModule,
    FontAwesomeModule,
  ],
  providers: [AuthService],
  bootstrap: [AppComponent]
})
export class AppModule { }
