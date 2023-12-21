import '@vaadin/tooltip/src/vaadin-tooltip.js';
import '@vaadin/polymer-legacy-adapter/style-modules.js';
import '@vaadin/icon/src/vaadin-icon.js';
import '@vaadin/icons/vaadin-iconset.js';
import '@vaadin/login/src/vaadin-login-form.js';
import '@vaadin/vertical-layout/src/vaadin-vertical-layout.js';
import '@vaadin/app-layout/src/vaadin-app-layout.js';
import '@vaadin/common-frontend/ConnectionIndicator.js';
import '@vaadin/vaadin-lumo-styles/color-global.js';
import '@vaadin/vaadin-lumo-styles/typography-global.js';
import '@vaadin/vaadin-lumo-styles/sizing.js';
import '@vaadin/vaadin-lumo-styles/spacing.js';
import '@vaadin/vaadin-lumo-styles/style.js';
import '@vaadin/vaadin-lumo-styles/vaadin-iconset.js';

const loadOnDemand = (key) => {
  const pending = [];
  if (key === '2613a8ed61bc812b4274fd5b04c3490a749b300ca2a2c4cc385cb7969a733a89') {
    pending.push(import('./chunks/chunk-b0d6bfbed4eac9f25c5a1025c273f0e46439a423e3549725ab3709994df6d866.js'));
  }
  if (key === '4467bdf7ef14746596306e8f36f66409f79ced6532c78fd119d7eeef1c691862') {
    pending.push(import('./chunks/chunk-c38fd1a10e0907ba170de66de99e5f24c8138a8594b91039aea7e76aa9f682ea.js'));
  }
  if (key === '31f0555c97c5b55184b424c5d53b882f7db7bfb5b45bb46438bba3b9c6ef2fc6') {
    pending.push(import('./chunks/chunk-48f44ebcca5907ce2c68509175bc05835b637400b77c2d01f0d2acb43f8a7c33.js'));
  }
  if (key === '299eb2cae9c5f55152755107b85b74b383cb4146eb26d16a6fbfa65fbd9a247e') {
    pending.push(import('./chunks/chunk-c38fd1a10e0907ba170de66de99e5f24c8138a8594b91039aea7e76aa9f682ea.js'));
  }
  if (key === 'b6c221b73b856907b0310711d025d73a1443e67039a26b8463144bf57f2c6a47') {
    pending.push(import('./chunks/chunk-c687c102159b63fba44b6353ba2206f7dce68b60092e1bae7a0d54f458d0e38e.js'));
  }
  if (key === '7fb049c99699249bd584a8aea3a88ed1f9bb7ec72c25d34cce7a02fb1dfd8969') {
    pending.push(import('./chunks/chunk-c38fd1a10e0907ba170de66de99e5f24c8138a8594b91039aea7e76aa9f682ea.js'));
  }
  if (key === '07fc2ea8ed889fc0e912dab74923bf93d6c4f6e34c2e3d2779d51a448b469cb6') {
    pending.push(import('./chunks/chunk-ec19c9caa869f204862c67458a72bffd92925b3d005ec7e8280d183682aea362.js'));
  }
  return Promise.all(pending);
}

window.Vaadin = window.Vaadin || {};
window.Vaadin.Flow = window.Vaadin.Flow || {};
window.Vaadin.Flow.loadOnDemand = loadOnDemand;