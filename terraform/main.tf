provider "google" {
  credentials = "${file(var.credentials_file)}"
  project     = "${var.project}"
  region      = "${var.region}"
}

resource "google_compute_network" "vpc_network" {
  name = "my-vpc-network"
}

resource "google_compute_firewall" "firewall" {
  name    = "allow-ssh-and-8080"
  network = google_compute_network.vpc_network.self_link

  allow {
    protocol = "tcp"
    ports    = ["22", "8080"]
  }

  source_tags = ["allow-ssh-and-8080"]
}

resource "google_compute_instance" "docker_host" {
  name         = "docker-host"
  machine_type = "n1-standard-1"
  zone         = "${var.zone}"
  boot_disk {
    initialize_params {
      image = "ubuntu-os-cloud/ubuntu-2004-lts"
      size  = "20GB"
    }
  }

  network_interface {
    network = google_compute_network.vpc_network.self_link
    access_config {
    }
  }

  tags = ["allow-ssh-and-8080"]
}

resource "google_compute_instance" "minikube" {
  name         = "minikube"
  machine_type = "n1-standard-2"
  zone         = "${var.zone}"
  boot_disk {
    initialize_params {
      image = "ubuntu-os-cloud/ubuntu-2004-lts"
      size  = "20GB"
    }
  }

  network_interface {
    network = google_compute_network.vpc_network.self_link
    access_config {
    }
  }

  tags = ["allow-ssh-and-8080"]
}

resource "google_compute_instance" "jenkins" {
  name         = "jenkins"
  machine_type = "n1-standard-1"
  zone         = "${var.zone}"
  boot_disk {
    initialize_params {
      image = "ubuntu-os-cloud/ubuntu-2004-lts"
    }
  }

  network_interface {
    network = google_compute_network.vpc_network.self_link
    access_config {
    }
  }

  tags = ["allow-ssh-and-8080"]
}

resource "google_compute_instance" "ansible" {
  name         = "ansible"
  machine_type = "n1-standard-1"
  zone         = "${var.zone}"
  boot_disk {
    initialize_params {
      image = "ubuntu-os-cloud/ubuntu-2004-lts"
    }
  }

  network_interface {
    network = google_compute_network.vpc_network.self_link
    access_config {
    }
  }

  tags = ["allow-ssh-and-8080"]
}
